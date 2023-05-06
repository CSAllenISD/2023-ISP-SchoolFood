const cookieParser = require('cookie-parser');
const createError = require('http-errors');
const logger = require('morgan');
const path = require('path');
const express = require("express");
const bodyParser = require("body-parser");
const passport = require('passport');
const googleAuth = require("./routes/authentication");
const jwt = require('jsonwebtoken');

require("./controllers/controller.tokenJWT");
require("dotenv/config");

const apiRouter = require('./routes/api');
const indexRouter = require('./routes/index');
const adminRouter = require('./routes/admin');
const dashboardRouter = require('./routes/dashboard');

const app = express();

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'jade');

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

app.route('/getDetails', passport.authenticate('jwt_strategy', { session: false }), (req, res)=>{
  console.log(req.user);
});

app.use('/', indexRouter);
app.use("/api", apiRouter);
app.use("/auth", googleAuth);
app.use('/dashboard', dashboardRouter);
app.use('/admin', adminRouter);

// catch 404 and forward to error handler
app.use(function(req, res, next) {
  next(createError(404));
});

// error handler
app.use(function(err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  console.error(err);

  // render the error page
  res.status(err.status || 500);
  next(createError(err.status || 500));
});

module.exports = app;
