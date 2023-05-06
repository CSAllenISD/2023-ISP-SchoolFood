const express = require('express');
const passport = require('passport');
const router = express.Router();
const GoogleStrategy = require( 'passport-google-oauth2' ).Strategy;
require('dotenv/config');
const { User } = require('../db.js');
require('./controller.tokenJWT');
const googleAuth = require('../routes/authentication');


const passportConfig = {
    "clientID":    process.env.CLIENT_ID,
    "clientSecret": process.env.CLIENT_SECRET,
    "callbackURL": process.env.MAIN_URL + "/auth/google/callback",
    "passReqToCallback"   : true
}

passport.use(
    new GoogleStrategy(
      passportConfig,
      async function (request, accessToken, refreshToken, profile, done) {
        const [user, created] = await User.findOrCreate({
          where: { email: profile._json.email },
          defaults: { name: profile.displayName, email: profile._json.email},
        });
        return done(created ? null : Error("Failed"), user);
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