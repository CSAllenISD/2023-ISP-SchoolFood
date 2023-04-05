var express = require('express');
var router = express.Router();
var { users } = require('../db');

/* GET users listing. */
router.get('/', async function(req, res, next) {
  
  console.log("REQUESTED users/");

  try {
    const allUsers = await users.find();
    res.json(allUsers)
  } catch(err) {
    console.error(err);
    next(err);
  }

});

module.exports = router;
