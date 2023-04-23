package com.schoolfood.sources

import com.schoolfood.R
import com.schoolfood.datamodel.restaraunt.FoodModel

class Foods {
    companion object {
        fun getFoods() : List<FoodModel> = listOf(
            FoodModel(
                restaurant = "Subway",
                name = "Ham Sandwich",
                image = R.drawable.ham_sandwich,
                price = 4.75F
            ),
            FoodModel(
                restaurant = "Subway",
                name = "Turkey Sandwich",
                image = R.drawable.ham_sandwich,
                price = 5.25F
            )
        )
    }
}