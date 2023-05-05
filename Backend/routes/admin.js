var express = require('express');
var router = express.Router();
const { sequelize, contact, User } = require('../db');

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
    const allContact = await contact.findAll();
    res.json(allContact)
  } catch(err) {
    console.error(err);
    next(err);
  }

});

router.get('/users', async function(req, res, next) {
  
  try {
    sequelize.sync().then(async () => {
      const allUsers = await User.findAll();
      res.json(allUsers)
    });
  } catch(err) {
    console.error(err);
    next(err);
  }

});

module.exports = router;
