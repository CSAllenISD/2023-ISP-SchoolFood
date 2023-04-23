const passport = require('passport');
const jwt = require("jsonwebtoken");
const express = require('express');
const { users } = require('../db');
const router = express.Router();

/* GET users listing. */
router.get('/', passport.authenticate("google", { scope: ["email", "profile"] }) );

router.get('/callback', passport.authenticate("google", { session: false }),
async function(req, res, next) {
  jwt.sign(
    { user: req.user },
    "secretKey",
    { expiresIn: "1h" },
    (err, token) => {
      if (err) {
        return res.json({
          token: null,
        });
      }
      res.json({
        token,
      });
    }
  );
});

module.exports = router;
