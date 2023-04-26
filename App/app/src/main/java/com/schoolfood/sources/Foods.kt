package com.schoolfood.sources

import com.schoolfood.R
import com.schoolfood.datamodel.restaraunt.FoodModel

class Foods {
    companion object {
        fun getFoods() : List<FoodModel> = listOf(
            FoodModel(
                restaurant = "Subway",
                name = "BMT Sub",
                image = R.drawable.bmt,
                price = 4.75F
            ),
            FoodModel(
                restaurant = "Subway",
                name = "Chicken/Teriyaki/Buffalo Sub",
                image = R.drawable.chicken_sandwich,
                price = 5.15F
            ),
            FoodModel(
                restaurant = "Subway",
                name = "Ham Sub",
                image = R.drawable.ham_sandwich,
                price = 4.25F
            ),
            FoodModel(
                restaurant = "Subway",
                name = "Chicken & Bacon Ranch Sub",
                image = R.drawable.chicken_bacon_sandwich,
                price = 5.80F
            ),
            FoodModel(
                restaurant = "Subway",
                name = "Steak & Cheese Sub",
                image = R.drawable.steak_sandwich,
                price = 5.40F
            ),
            FoodModel(
                restaurant = "Subway",
                name = "Turkey Sub",
                image = R.drawable.turkey_sandwich,
                price = 4.75F
            ),
            FoodModel(
                restaurant = "Subway",
                name = "Veggie Sub",
                image = R.drawable.veggie_sandwich,
                price = 4.00F
            ),
            FoodModel(
                restaurant = "Subway",
                name = "Meatball Sub",
                image = R.drawable.meatball_sandwich,
                price = 4.80F
            ),
            FoodModel(
                restaurant = "Subway",
                name = "Chips",
                image = R.drawable.lays,
                price = 1.50F
            ),
            FoodModel(
                restaurant = "Subway",
                name = "Soft Drink",
                image = R.drawable.drinks,
                price = 1.25F
            )
        )
    }
}