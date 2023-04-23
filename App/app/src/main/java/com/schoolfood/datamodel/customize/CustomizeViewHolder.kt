package com.schoolfood.datamodel.customize

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.slider.Slider
import com.schoolfood.R

class CustomizeViewHolder (customizeAdapter: CustomizeAdapter, itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val adapter : CustomizeAdapter = customizeAdapter;

    private fun bindSliderFree(item: CustomizeModel.SliderFree) {
        val label : TextView = itemView.findViewById(R.id.label)
        label.text = item.name + " (" + item.chosenOption + ")"

        val image : ImageView = itemView.findViewById(R.id.customize_image)
        image.setImageResource(item.imagePath)

        val slider: Slider = itemView.findViewById(R.id.slider)
        slider.setLabelFormatter { value: Float ->
            item.options[value.toInt()]
        }
        slider.value = item.chosenIndex.toFloat()
        slider.valueFrom = 0.0f
        slider.valueTo = (item.options.size - 1).toFloat()

        slider.addOnChangeListener(Slider.OnChangeListener { _, value, _ ->
            run {
                item.chosenIndex = value.toInt()
                item.chosenOption = item.options[value.toInt()]
                label.text = item.name + " (" + item.chosenOption + ")"
            }
        })
    }

    private fun bindSlider(item: CustomizeModel.Slider) {
        val label : TextView = itemView.findViewById(R.id.label)
        label.text = item.name + " (" + item.chosenOption + ")"

        val cost : TextView = itemView.findViewById(R.id.cost)
        cost.text = "$" + String.format("%.2f", item.priceOptions[item.chosenIndex])

        val image : ImageView = itemView.findViewById(R.id.customize_image)
        image.setImageResource(item.imagePath)

        val slider: Slider = itemView.findViewById(R.id.slider)
        slider.setLabelFormatter { value: Float ->
            item.options[value.toInt()] + " (" + item.priceOptions[value.toInt()] + ")"
        }
        slider.value = item.chosenIndex.toFloat()
        slider.valueFrom = 0.0f
        slider.valueTo = (item.options.size - 1).toFloat()

        slider.addOnChangeListener(Slider.OnChangeListener { _, value, _ ->
            run {
                item.chosenIndex = value.toInt()
                item.chosenOption = item.options[value.toInt()]
                label.text = item.name + " (" + item.chosenOption + ")"
                cost.text = "$" + String.format("%.2f", item.priceOptions[item.chosenIndex])
            }
        })
    }

    private fun bindSeparator(item: CustomizeModel.Separator) {}

    fun bind(dataModel: CustomizeModel) {
        when (dataModel) {
            is CustomizeModel.Slider -> bindSlider(dataModel)
            is CustomizeModel.SliderFree -> bindSliderFree(dataModel)
            is CustomizeModel.Separator -> bindSeparator(dataModel)
        }
    }
}