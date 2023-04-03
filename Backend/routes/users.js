var express = require('express');
var router = express.Router();
var { users } = require('../db');

/* GET users listing. */
router.get('/', function(req, res, next) {
  
  console.log("REQUESTED");

  users.find({}, function(err, result) {    
    if (err) {
      console.log(err);
    } else {
      res.json(result);
    }
  });

});

module.exports = router;
