package com.schoolfood.sources

import com.schoolfood.R
import com.schoolfood.datamodel.customize.CustomizeModel

class Customizations {
    companion object {
        private val subwaySandwich = listOf(
            CustomizeModel.Slider(
                name = "Cheese",
                options = listOf("None", "Regular", "Extra"),
                priceOptions = listOf(0.00, 0.00, 0.30),
                chosenOption = "None",
                imagePath = R.drawable.cheese
            ),
            CustomizeModel.SliderFree(
                name = "Lettuce",
                options = listOf("None", "Light", "Regular", "Extra"),
                chosenOption = "None",
                imagePath = R.drawable.lettuce
            ),
            CustomizeModel.SliderFree(
                name = "Cucumber",
                options = listOf("None", "Light", "Regular", "Extra"),
                chosenOption = "None",
                imagePath = R.drawable.lettuce
            ),
            CustomizeModel.SliderFree(
                name = "Green Pepper",
                options = listOf("None", "Light", "Regular", "Extra"),
                chosenOption = "None",
                imagePath = R.drawable.lettuce
            ),
            CustomizeModel.SliderFree(
                name = "Onion",
                options = listOf("None", "Light", "Regular", "Extra"),
                chosenOption = "None",
                imagePath = R.drawable.lettuce
            ),
            CustomizeModel.SliderFree(
                name = "Jalapeno",
                options = listOf("None", "Light", "Regular", "Extra"),
                chosenOption = "None",
                imagePath = R.drawable.lettuce
            ),
            CustomizeModel.SliderFree(
                name = "Tomato",
                options = listOf("None", "Light", "Regular", "Extra"),
                chosenOption = "None",
                imagePath = R.drawable.lettuce
            ),
            CustomizeModel.SliderFree(
                name = "Black Olive",
                options = listOf("None", "Light", "Regular", "Extra"),
                chosenOption = "None",
                imagePath = R.drawable.lettuce
            ),
            CustomizeModel.SliderFree(
                name = "Pickle",
                options = listOf("None", "Light", "Regular", "Extra"),
                chosenOption = "None",
                imagePath = R.drawable.lettuce
            ),
            CustomizeModel.Separator(),
            CustomizeModel.SliderFree(
                name = "Chipotle Southwest",
                options = listOf("None", "Light", "Regular", "Extra"),
                chosenOption = "None",
                imagePath = R.drawable.chipotle
            ),
            CustomizeModel.SliderFree(
                name = "Mayonnaise",
                options = listOf("None", "Light", "Regular", "Extra"),
                chosenOption = "None",
                imagePath = R.drawable.lettuce
            ),
            CustomizeModel.SliderFree(
                name = "Honey Mustard",
                options = listOf("None", "Light", "Regular", "Extra"),
                chosenOption = "None",
                imagePath = R.drawable.lettuce
            ),
            CustomizeModel.SliderFree(
                name = "Smoky BBQ",
                options = listOf("None", "Light", "Regular", "Extra"),
                chosenOption = "None",
                imagePath = R.drawable.lettuce
            ),
            CustomizeModel.Separator(),
            CustomizeModel.Toggle(
                name = "Extra Meat",
                selectedPrice = 1.50,
                imagePath = R.drawable.meat
            ),
            CustomizeModel.Slider(
                name = "Bacon",
                options = listOf("None", "One", "Two"),
                priceOptions = listOf(0.00, 0.65, 1.30),
                chosenOption = "None",
                imagePath = R.drawable.bacon
            )
        )

        private val customizations = mapOf(
            "BMT Sub" to subwaySandwich,
            "Chicken/Teriyaki/Buffalo Sub" to subwaySandwich,
            "Ham Sub" to subwaySandwich,
            "Chicken & Bacon Ranch Sub" to subwaySandwich.filter {
                if (it is CustomizeModel.Slider && it.name == "Bacon")
                    return@filter false
                return@filter true
              },
            "Steak & Cheese Sub" to subwaySandwich,
            "Turkey Sub" to subwaySandwich,
            "Veggie Sub" to subwaySandwich,
            "Meatball Sub" to subwaySandwich,
            "Chips" to listOf(CustomizeModel.DropdownFree(
                name = "Type",
                options = listOf("Lays", "Doritos"),
                imagePaths = listOf(R.drawable.lays, R.drawable.doritos)
            )),
            "Soft Drink" to listOf(CustomizeModel.DropdownFree(
                name = "Brand",
                options = listOf("CocaCola", "Sprite", "Fanta"),
                imagePaths = listOf(R.drawable.drinks)
            )),
        )

        fun getCustomizations(foodItem: String) : List<CustomizeModel> {
            return customizations.getOrDefault(foodItem, listOf())
        }
    }
}