var express = require('express');
var router = express.Router();
const { contact } = require('../db');

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
  res.render('about', { title: 'PreOrder' });
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

    const results = await contact.find({ name: req.body.name, email: req.body.email, subject: req.body.subject, content: req.body.content });
    if (results.length) {
      // Already submitted!
      res.render('contact', { title: 'Contact', didSubmit: false });
      return;
    }

    var contactDetails = new contact({
      name: req.body.name,
      email: req.body.email,
      subject: req.body.subject,
      content: req.body.content
    });

    try {
      await contactDetails.save()
      res.render('contact', { title: 'Contact', didSubmit: true });
    } catch(err) {
      console.error(err);
      res.render('contact', { title: 'Contact', didSubmit: false });
    }
  } else {
    res.render('contact', { title: 'Contact', didSubmit: false });
  }
});

module.exports = router;
