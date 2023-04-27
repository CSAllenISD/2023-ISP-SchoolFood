const mongoose = require('mongoose');

async function connect() {
  await mongoose.connect('mongodb://127.0.0.1:27017/schoolFood');
  console.log("DB is successfully connected!")
}

const passportLocalMongoose = require('passport-local-mongoose');
const findOrCreate = require('mongoose-findorcreate');

const User = new mongoose.Schema({
      name: String,
      email: {
        type: String,
        lowercase: true,
        unique: true,
        required: [true, "can't be blank"],
        match: [/\S+@\S+\.\S+/, 'is invalid'],
        index: true,
      },
      balance: Number,
      password: {
        type: String,
        trim: true,
        minlength: 6,
        maxlength: 60,
      },
      token: {
        type: String,
        unique: true
      }
});

User.plugin(findOrCreate);
User.plugin(passportLocalMongoose);

const contactSchema = new mongoose.Schema({
  name: String,
  email: String,
  subject: String,
  content: String
});

const contact = mongoose.model('Contact', contactSchema);

module.exports = {
  connect: connect,
  User: mongoose.model('User', User),
  contact: contact
};