package com.schoolfood.datamodel.customize

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import android.widget.*
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
                try {
                    item.chosenIndex = value.toInt()
                    item.chosenOption = item.options[value.toInt()]
                    label.text = item.name + " (" + item.chosenOption + ")"
                } catch (e: Exception) {}
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
                try {
                    item.chosenIndex = value.toInt()
                    item.chosenOption = item.options[value.toInt()]
                    label.text = item.name + " (" + item.chosenOption + ")"
                    cost.text = "$" + String.format("%.2f", item.priceOptions[item.chosenIndex])
                } catch (e: Exception) {}
            }
        })
    }

    private fun bindToggle(item: CustomizeModel.Toggle) {
        val label : TextView = itemView.findViewById(R.id.label)
        label.text = item.name

        val cost : TextView = itemView.findViewById(R.id.cost)
        cost.text = "$" + String.format("%.2f", item.selectedPrice)
        if(item.chosen) cost.setTextColor(Color.argb(125, 200, 200, 200))
        else cost.setTextColor(Color.argb(255, 0, 255, 0))

        val image : ImageView = itemView.findViewById(R.id.customize_image)
        image.setImageResource(item.imagePath)

        val toggle: Switch = itemView.findViewById(R.id.toggle)
        toggle.isChecked = item.chosen

        toggle.setOnClickListener {
            run {
                try {
                    item.chosen = (it as Switch).isChecked
                    if(item.chosen) cost.setTextColor(Color.argb(125, 200, 200, 200))
                    else cost.setTextColor(Color.argb(255, 0, 255, 0))
                } catch (e: Exception) {}
            }
        }
    }

    private fun bindDropDownFree(item: CustomizeModel.DropdownFree) {
        val label : TextView = itemView.findViewById(R.id.label)
        label.text = item.name

        val image : ImageView = itemView.findViewById(R.id.customize_image)
        image.setImageResource(item.imagePaths[item.chosenIndex])

        val dropdown: Spinner = itemView.findViewById(R.id.dropdown)
        val dropDownAdapter = ArrayAdapter(adapter.context, android.R.layout.simple_spinner_item, item.options)
        dropdown.adapter = dropDownAdapter
        dropdown.setSelection(item.chosenIndex)

        dropdown.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                item.chosenIndex = position

                if (item.imagePaths.size > item.chosenIndex)
                    image.setImageResource(item.imagePaths[item.chosenIndex])
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                item.chosenIndex = -1
            }
        }
    }

    private fun bindSeparator(item: CustomizeModel.Separator) {}

    fun bind(dataModel: CustomizeModel) {
        when (dataModel) {
            is CustomizeModel.DropdownFree -> bindDropDownFree(dataModel)
            is CustomizeModel.Toggle -> bindToggle(dataModel)
            is CustomizeModel.Slider -> bindSlider(dataModel)
            is CustomizeModel.SliderFree -> bindSliderFree(dataModel)
            is CustomizeModel.Separator -> bindSeparator(dataModel)
        }
    }
}