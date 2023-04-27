const jwt = require('jsonwebtoken');
const express = require('express');
const router = express.Router();
require('../controllers/controller.auth');
require('dotenv/config');
const passport = require('passport');
const { User } = require('../db.js');

router.get('/', (req, res) => {
  const token = req.cookies['jwt'];
  console.log(`User TOKEN: ${token}`);
  if (!token) {
    res.redirect('/auth/google');
  } else {
    jwt.verify(token, process.env["JWT_SECRET_KEY"], async (err, decodedToken) => {
      if (err || !decodedToken.id) {
        console.log(err.message);
        res.redirect('/auth/google');
      } else {
        console.log(`User ID: ${decodedToken.id}`);
        try {
          const user = await User.findById(decodedToken.id);
          console.log(`User: ${user}`)

          res.render('dashboard', { title: "Dashboard", error: false, errorMsg: '', user: user });
        }
        catch (err) {
          console.log(err.message);
          res.redirect('/auth/google');
        }
      }
    });
  }
});

module.exports = router;