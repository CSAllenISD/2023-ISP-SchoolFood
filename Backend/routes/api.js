const jwt = require('jsonwebtoken');
const express = require('express');
const router = express.Router();
require('../controllers/controller.auth');
require('dotenv/config');
const passport = require('passport');
const { sequelize, User } = require('../db.js');

function apiAuth(req, res) {
  const token = req.header('Authorization').replace('Bearer ', '')
  console.log(`User TOKEN: ${token}`);
  if (!token) {
    res.status(401).send({ error: true, msg: 'No token found' });
  } else {
    jwt.verify(token, process.env["JWT_SECRET_KEY"], async (err, decodedToken) => {
      if (err || !decodedToken?.user?.email) {
        res.status(500).send({ error: true, msg: 'Invalid token' });
      } else {
        try {
          sequelize.sync().then(async () => {
            const user = await User.findOne({
              where: {
                email: decodedToken.user.email,
                token: token
              }
            }, { raw: true });
            if (!user) {
              res.send({ error: true, msg: 'Invalid user' });
              return null;
            }
            return user;
          });
        }
        catch (err) {
          console.log(err.message);
          res.status(500).send({ error: true, msg: err.message });
        }
      }
    });
  }
}

router.post('/balance', (req, res) => {
  const user = apiAuth(req, res);
  if (!user) return;

  res.send({ error: false, value: user.balance });
});

router.post('/purchase', (req, res) => {
  const user = apiAuth(req, res);
  if (!user) return;

  console.log(req.params.order);

  res.send({ error: false, value: user.balance });
});

module.exports = router;