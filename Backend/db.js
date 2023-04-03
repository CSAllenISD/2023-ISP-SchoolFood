const mongoose = require('mongoose');

async function connect() {
  await mongoose.connect('mongodb://127.0.0.1:27017/schoolFood');
  console.log("DB is successfully connected!")
}

const usersSchema = new mongoose.Schema({
  name: String,
  token: String,
  email: String,
  balance: Number
});

const users = mongoose.model('Users', usersSchema);

module.exports = {
  connect: connect,
  users: users
};