var express = require('express');
var router = express.Router();
const { sequelize, User, contact } = require('../db');

/* GET home page. */
router.get('/', function(req, res, next) {
  console.log("REQUESTED /");
  
  try {
    res.render('index', { title: 'SchoolFood' });
  } catch (err) {
    console.error(err)
    next(err)
  }
});

router.get('/preorder', function(req, res, next) {
    res.render('preorder', { title: 'PreOrder' });
});
router.get('/order', function(req, res, next) {
  res.render('preorder', { title: 'PreOrder' });
});

router.get('/about', function(req, res, next) {
  res.render('about', { title: 'About Us' });
});

router.get('/login', function(req, res, next) {
  res.render('login', { title: 'Login', error: false, errorMsg: '' });
});

router.post('/login', async function(req, res, next) {
  console.log("Requested POST login");

  var errors = false;
  if (!req.body.password) errors = true;
  if (!req.body.email || !req.body.email.includes("@")) errors = true;

  if( !errors ) {
    // No errors were found.  Passed Validation!
    console.log("No errors!");

    sequelize.sync().then(async () => {
      const results = await User.findOne({where: { email: req.body.email, password: req.body.password }});
      if (results.length != 0) {
        // No account
        res.render('login', { title: 'Login', error: true, errorMsg: "Account with that email and password do not exist." });
        return;
      }
      
      res.render('login', { title: 'Login', error: false, errorMsg: "" });
    });
  } else {
    res.render('login', { title: 'Login', error: true, errorMsg: "Invalid Email or Password." });
  }
});


router.get('/contact', function(req, res, next) {
  res.render('contact', { title: 'Contact', didSubmit: false });
});

router.post('/contact', async function(req, res, next) {
  console.log("Requested POST contact");

  var errors = false;
  if (!req.body.name || !req.body.subject || !req.body.content) errors = true;
  if (!req.body.email || !req.body.email.includes("@")) errors = true;

  if( !errors ) {
    // No errors were found.  Passed Validation!
    console.log("No errors!");
    sequelize.sync().then(async () => {
      const results = await contact.findOne({ where: { name: req.body.name, email: req.body.email, subject: req.body.subject, content: req.body.content }});
      if (results.length) {
        // Already submitted!
        res.render('contact', { title: 'Contact', didSubmit: false });
        return;
      }

      try {
        await contact.create({
          name: req.body.name,
          email: req.body.email,
          subject: req.body.subject,
          content: req.body.content
        });
        res.render('contact', { title: 'Contact', didSubmit: true });
      } catch(err) {
        console.error(err);
        res.render('contact', { title: 'Contact', didSubmit: false });
      }
    })
  } else {
    res.render('contact', { title: 'Contact', didSubmit: false });
  }
});

module.exports = router;
