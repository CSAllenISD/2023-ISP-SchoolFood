const jwt = require('jsonwebtoken');
const express = require('express');
const passport = require('passport');
const router = express.Router();
const GoogleStrategy = require( 'passport-google-oauth2' ).Strategy;
require('dotenv/config');
const { User } = require('../db.js');
require('./controller.tokenJWT');

const passportConfig = {
    "clientID":    process.env.CLIENT_ID,
    "clientSecret": process.env.CLIENT_SECRET,
    "callbackURL": process.env.MAIN_URL + "/auth/google/callback",
    "passReqToCallback"   : true
}

passport.use(
    new GoogleStrategy(
      passportConfig,
      async function (req, accessToken, refreshToken, profile, done) {
        console.log(profile);
        const token = jwt.sign( { user: { "email": profile._json.email }, id: profile.id }, process.env["JWT_SECRET_KEY"] );

        const [user, created] = await User.findOrCreate({
          where: { email: profile._json.email },
          defaults: { name: profile.displayName, email: profile._json.email },
        });

        if (user && user.token == null) {
          user.token = token;
          user.save();
        }

        return done(null, user);
      }
    )
);

passport.serializeUser(function(user, done){
    done(null, user);
});

passport.serializeUser(function(user, done){
    done(null, user);
});

module.exports=router;