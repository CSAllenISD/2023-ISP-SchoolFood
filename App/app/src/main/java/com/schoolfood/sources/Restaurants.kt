package com.schoolfood.sources

import com.schoolfood.R
import com.schoolfood.datamodel.home.RestaurantModel

class Restaurants {
    companion object {
        fun getRestaurants() : List<RestaurantModel> = listOf(
            RestaurantModel(
                name = "Subway",
                image = R.drawable.subway_logo
            ),
            RestaurantModel(
                name = "PizzaHut",
                image = R.drawable.pizzahut_logo
            )
        )
    }
}