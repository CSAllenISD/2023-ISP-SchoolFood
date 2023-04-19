package com.schoolfood.datamodel.customize

sealed class CustomizeModel {
    data class Separator(
        var padding : String = "5px"
    ) : CustomizeModel()
    data class SliderFree(
        var name : String,
        var options : List<String>,
        var chosenOption : String,
        var imagePath : String
    ) : CustomizeModel()
    data class Slider(
        var name : String,
        var options : List<String>,
        var chosenOption : String,
        var imagePath : String,
        var priceOptions : List<Double>
    ) : CustomizeModel()
}