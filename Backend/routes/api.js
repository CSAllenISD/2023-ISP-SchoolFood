const jwt = require('jsonwebtoken');
const express = require('express');
const router = express.Router();
require('../controllers/controller.auth');
require('dotenv/config');
const passport = require('passport');
const { sequelize, User, Order } = require('../db.js');
const { findOrderPrice } = require("../orderUtil.js")

async function apiAuth(req, res) {
    const token = req.header('Authorization').replace('Bearer ', '')
    console.log(`User TOKEN: ${token}`);
    return new Promise(resolve => {
	if (!token) {
	    res.status(401).send({ error: true, msg: 'No token found' });
	    resolve(null);
	} else {
	    jwt.verify(token, process.env["JWT_SECRET_KEY"], async (err, decodedToken) => {
		if (err || !decodedToken?.user?.email) {
		    res.status(500).send({ error: true, msg: 'Invalid token' });
		    resolve(null);
		} 
		try {
		    await sequelize.sync()
		    const user = await User.findOne({
			where: {
			    email: decodedToken.user.email,
			    token: token
			}
		    }, { raw: true });
		    if (!user) {
			res.send({ error: true, msg: 'Invalid user' });
			resolve(null);
		    }

		    console.log(`User: ${user}`)
		    resolve(user);
		}
		catch (err) {
		    console.error(err);
		    res.status(500).send({ error: true, msg: err.message });
		    resolve(null)
		}
	    });
	}
    });
}

router.post('/balance', async (req, res) => {
    const user = await apiAuth(req, res);
    console.log(`User: ${user}`)
    if (!user) return;

    res.send({ error: false, value: user.balance });
});

router.post('/purchase', async (req, res) => {
    const user = await apiAuth(req, res);
    if (!user) return;

    const params = req.body;
    
    const order = params?.order
    if (!order) {
	res.status(500).send({ error: true, msg: "No order provided." });
	return;
    }

    const price = findOrderPrice(order);

    if (isNaN(price)) {
	console.log(order);
	console.log(price);
	res.status(500).send({ error: true, msg: "Invalid Price! Contact Staff." });
	return;
    }

    if(price > user.balance) {
	res.status(500).send({ error: true, msg: "Not enough balance" });
	return;
    }

    user.balance -= price;
    user.save();

    var orderStr = "";
    order.forEach(dish => {
	orderStr += `${dish.name}\n`
	dish.customizations.forEach(c => {
	    orderStr += `    ${c}\n`;
	});
    });

    await Order.create({ student: user.name, order: orderStr, price: price });

    res.send({ error: false, value: user.balance });
});

module.exports = router;
