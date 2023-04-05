var express = require('express');
var router = express.Router();

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
  res.render('contact', { title: 'PreOrder' });
});

module.exports = router;
