const jwt = require('jsonwebtoken');
const express = require('express');
const router = express.Router();
require('../controllers/controller.auth');
require('dotenv/config');
const passport = require('passport');
const { sequelize, User } = require('../db.js');

const baseFrontendUrl = process.env["MAIN_URL"];

router.get('/', (req, res) => {
  const token = req.cookies['jwt'];
  console.log(`User TOKEN: ${token}`);
  if (!token) {
    res.redirect(`${baseFrontendUrl}/auth/google`);
  } else {
    jwt.verify(token, process.env["JWT_SECRET_KEY"], async (err, decodedToken) => {
      if (err || !decodedToken?.user?.email) {
        if (err) console.error(err);
        else console.log(decodedToken)
        res.redirect(`${baseFrontendUrl}/auth/google`);
      } else {
        console.log(`Decoded: ${decodedToken}`);
        try {
          sequelize.sync().then(async () => {
            const user = await User.findOne({
              where: {
                email: decodedToken.user.email
              }
            }, { raw: true });
            if (!user) {
              res.redirect(`${baseFrontendUrl}/auth/google`);
              return;
            }

            console.log(`User: ${user}`)
            res.render('dashboard', { title: "Dashboard", error: false, errorMsg: '', user: user });
          });
        }
        catch (err) {
          console.log(err.message);
          res.redirect(`${baseFrontendUrl}/auth/google`);
        }
      }
    });
  }
});

module.exports = router;