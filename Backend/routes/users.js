var express = require('express');
var router = express.Router();
var db = require('../db');

/* GET users listing. */
router.get('/', function(req, res, next) {
  db.collection('mammals').find().toArray((err, result) => {
    if (err) throw err

    res.send(result);
  })

});

module.exports = router;
