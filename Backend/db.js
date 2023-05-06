const { Sequelize, DataTypes } = require('sequelize');
require("dotenv/config")

var sequelize = new Sequelize(
  process.env["MYSQL_DB_NAME"],
  process.env["MYSQL_USER"],
  process.env["MYSQL_PASSWORD"],
  {
    host: 'db',
    dialect: 'mysql',
  }
);

async function connect() {
  await sequelize.authenticate(); 
  console.log("DB is successfully connected!")
}

const User = sequelize.define("users", {
      name: {
        type: DataTypes.STRING
      },
      email: {
        type: DataTypes.STRING,
        unique: true,
        allowNull: false
      },
      balance: {
        type: DataTypes.FLOAT,
        defaultValue: 0.0
      },
      password: {
        type: DataTypes.STRING
      },
      token: {
        type: DataTypes.STRING,
        unique: true
      }
});

sequelize.sync().then(() => {
  console.log('Users table created successfully!');
}).catch((error) => {
  console.error('Unable to create table : ', error);
});

const Contact = sequelize.define("contact", {
  name: {
    type: DataTypes.STRING
  },
  email: {
    type: DataTypes.STRING
  },
  subject: {
    type: DataTypes.STRING
  },
  content: {
    type: DataTypes.STRING
  }
});

module.exports = {
  sequelize: sequelize,
  connect: connect,
  User: User,
  contact: Contact
};
