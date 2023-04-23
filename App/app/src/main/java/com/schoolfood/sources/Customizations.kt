package com.schoolfood.sources

import com.schoolfood.R
import com.schoolfood.datamodel.customize.CustomizeModel
import com.schoolfood.datamodel.restaraunt.FoodModel

class Customizations {
    companion object {
        private val customizations = mapOf(
            "Ham Sandwich" to listOf(
                CustomizeModel.SliderFree(
                    name = "Lettuce",
                    options = listOf("None", "Light", "Regular", "Extra"),
                    chosenOption = "None",
                    imagePath = R.drawable.lettuce
                ),
                CustomizeModel.Separator(),
                CustomizeModel.Slider(
                    name = "Bacon",
                    options = listOf("None", "One", "Two"),
                    priceOptions = listOf(0.00, 0.50, 1.00),
                    chosenOption = "None",
                    imagePath = R.drawable.bacon
                )
            )
        )

        fun getCustomizations(foodItem: String) : List<CustomizeModel> {
            return customizations.getOrDefault(foodItem, listOf())
        }
    }
}