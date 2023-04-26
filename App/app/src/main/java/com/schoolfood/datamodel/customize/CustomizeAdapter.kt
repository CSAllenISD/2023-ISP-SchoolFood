package com.schoolfood.datamodel.customize

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.schoolfood.MainActivity
import com.schoolfood.R

class CustomizeAdapter(activity: MainActivity) : RecyclerView.Adapter<CustomizeViewHolder>() {

    val context = activity
    private val adapterData = mutableListOf<CustomizeModel>()
    private lateinit var scoutingViewHolder: CustomizeViewHolder;

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomizeViewHolder {

        val layout = when (viewType) {
            TYPE_SLIDER_FREE -> R.layout.sliderfree
            TYPE_SLIDER -> R.layout.slider
            TYPE_SEPARATOR -> R.layout.separator
            TYPE_SWITCH -> R.layout.toggle
            TYPE_DROPDOWN_FREE -> R.layout.dropdownfree
            else -> throw IllegalArgumentException("Invalid type")
        }

        val view = LayoutInflater
            .from(parent.context)
            .inflate(layout, parent, false)

        scoutingViewHolder = CustomizeViewHolder(this, view)

        return scoutingViewHolder
    }


    override fun onBindViewHolder(
        holder: CustomizeViewHolder,
        position: Int
    ) {
        holder.bind(adapterData[position])
    }

    override fun getItemCount(): Int = adapterData.size

    override fun getItemViewType(position: Int): Int {
        return when (adapterData[position]) {
            is CustomizeModel.SliderFree -> TYPE_SLIDER_FREE
            is CustomizeModel.Slider -> TYPE_SLIDER
            is CustomizeModel.DropdownFree -> TYPE_DROPDOWN_FREE
            is CustomizeModel.Toggle -> TYPE_SWITCH
            else -> TYPE_SEPARATOR
        }
    }

    fun getData(): List<CustomizeModel> {
        return adapterData;
    }

    fun setData(data: List<CustomizeModel>) {
        adapterData.apply {
            clear()
            addAll(data)
        }

        notifyDataSetChanged()
    }

    companion object {
        private const val TYPE_SLIDER_FREE = 0
        private const val TYPE_SLIDER = 1
        private const val TYPE_SEPARATOR = 2
        private const val TYPE_SWITCH = 3
        private const val TYPE_DROPDOWN_FREE = 4
    }
}