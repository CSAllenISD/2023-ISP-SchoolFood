package com.schoolfood.datamodel.customize

sealed class CustomizeModel {
    data class Separator(
        var padding : String = "5px"
    ) : CustomizeModel()
    data class SliderFree(
        var name : String,
        var options : List<String>,
        var chosenOption : String,
        var chosenIndex : Int = 0,
        var imagePath : Int
    ) : CustomizeModel()
    data class Slider(
        var name : String,
        var options : List<String>,
        var chosenOption : String,
        var chosenIndex : Int = 0,
        var imagePath : Int,
        var priceOptions : List<Double>
    ) : CustomizeModel()
    data class Toggle(
        var name : String,
        var chosen : Boolean = false,
        var selectedPrice : Double,
        var imagePath : Int
    ) : CustomizeModel()
    data class DropdownFree (
        var name : String,
        var options : List<String>,
        var imagePaths : List<Int>,
        var chosenIndex : Int = 0
    ) : CustomizeModel()
}