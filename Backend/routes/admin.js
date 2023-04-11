var express = require('express');
var router = express.Router();
const { contact } = require('../db');

/* GET home page. */
router.get('/', function(req, res, next) {
  console.log("REQUESTED /admin");
  
  try {
    res.render('index', { title: 'SchoolFood' });
  } catch (err) {
    console.error(err)
    next(err)
  }
});

router.get('/contact', async function(req, res, next) {
  
  try {
    const allContact = await contact.find();
    res.json(allContact)
  } catch(err) {
    console.error(err);
    next(err);
  }

});

module.exports = router;
