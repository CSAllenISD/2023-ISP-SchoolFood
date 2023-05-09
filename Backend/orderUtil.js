function foodModel (restaurant, name, basePrice, customizations=[]) {
    this.restaurant = restaurant;
    this.name = name;
    this.basePrice = basePrice;
    this.customizations = customizations;
}

const foods = [
    new foodModel("Subway", "BMT Sub", 4.75),
    new foodModel("Subway", "Chicken/Teriyaki/Buffalo Sub", 5.15),
    new foodModel("Subway", "Ham Sub", 4.25),
    new foodModel("Subway", "Chicken & Bacon Ranch Sub", 5.80),
    new foodModel("Subway", "Steak & Cheese Sub", 5.40),
    new foodModel("Subway", "Turkey Sub", 4.75),
    new foodModel("Subway", "Veggie Sub", 4.00),
    new foodModel("Subway", "Meatball Sub", 4.80),
    new foodModel("Subway", "Chips", 1.50),
    new foodModel("Subway", "Soft Drink", 1.25)
]

const getBaseFood = (name) => {
    return foods.find(a => a.name == name);
}

function customizationModel (name, options, priceOptions) {
    this.name = name;
    this.options = options;
    this.priceOptions = priceOptions;
}

const customizations = [
    new customizationModel("Cheese", ["None", "Regular", "Extra"], [0, 0, 0.30]),
    new customizationModel("Lettuce", ["None", "Light", "Regular", "Extra"], [0, 0, 0, 0]),
    new customizationModel("Cucumber", ["None", "Light", "Regular", "Extra"], [0, 0, 0, 0]),
    new customizationModel("Green Pepper", ["None", "Light", "Regular", "Extra"], [0, 0, 0, 0]),
    new customizationModel("Onion", ["None", "Light", "Regular", "Extra"], [0, 0, 0, 0]),
    new customizationModel("Jalapeno", ["None", "Light", "Regular", "Extra"], [0, 0, 0, 0]),
    new customizationModel("Tomato", ["None", "Light", "Regular", "Extra"], [0, 0, 0, 0]),
    new customizationModel("Black Olive", ["None", "Light", "Regular", "Extra"], [0, 0, 0, 0]),
    new customizationModel("Pickle", ["None", "Light", "Regular", "Extra"], [0, 0, 0, 0]),
    new customizationModel("Chiptole Southwest", ["None", "Light", "Regular", "Extra"], [0, 0, 0, 0]),
    new customizationModel("Mayonnaise", ["None", "Light", "Regular", "Extra"], [0, 0, 0, 0]),
    new customizationModel("Honey Mustard", ["None", "Light", "Regular", "Extra"], [0, 0, 0, 0]),
    new customizationModel("Smoky BBQ", ["None", "Light", "Regular", "Extra"], [0, 0, 0, 0]),
    new customizationModel("Extra Meat", [false, true], [0, 1.50]),
    new customizationModel("Bacon", ["None", "One", "Two"], [0, 0.65, 1.30])
]

/**
 * Finds the price for a specific dish.
 * @param {string} name - The name of the dish.
 * @param {[string]} customizations - List of customizations in name:value format.
 */

const findPrice = (name, customizs) => {
    console.log("Finding Price for: " + name);
    const dish = getBaseFood(name);
    if (!dish) return -1;
    
    var price = dish.basePrice;
    customizs.forEach(customization => {
	const args = customization.split(":");
	const cName = args[0];
	const value = args[1];
	const c = customizations.find(a => a.name == cName);
	if (c) {
	    const i = c.options.indexOf(value);
	    if(i != -1) price += c.priceOptions[i];
	} else {
	    console.log("Cant find customization: \"" + cName + "\"");
	}
    });

    console.log("Price Found: " + price);
    return price;
}

/**
 * Finds the price for an order.
 * @param {[{name: string, customizations: [string]}]} dishes - List of dishes.
 */
const findOrderPrice = (dishes) => {
    var totalPrice = 0.00;
    
    dishes.forEach(dish => {
	const price = findPrice(dish.name, dish.customizations)
	if (price != -1) {
	    totalPrice += price
	}
    });

    return totalPrice;
}

module.exports = {
    findPrice: findPrice,
    findOrderPrice: findOrderPrice
}
