package com.schoolfood.datamodel.cart

data class CartFoodModel(
    var id: String,
    var restaurant: String,
    var name: String,
    var image: Int,
    var price: Float,
    val customizations: List<String>
)