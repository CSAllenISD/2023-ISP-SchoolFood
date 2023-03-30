var usersDB;

const MongoClient = require('mongodb').MongoClient

MongoClient.connect('mongodb://localhost:27017/users', (err, client) => {
  if (err) throw err

  usersDB = client.db('users')
})

module.exports = usersDB;