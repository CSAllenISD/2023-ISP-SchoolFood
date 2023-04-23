package com.schoolfood.datamodel.restaraunt

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.schoolfood.MainActivity
import com.schoolfood.R

class RestaurantAdapter(fragment: Fragment, activity: MainActivity) : RecyclerView.Adapter<RestaurantViewHolder>() {

    val fragment = fragment
    val context = activity
    private val adapterData = mutableListOf<FoodModel>()
    private lateinit var scoutingViewHolder: RestaurantViewHolder;

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RestaurantViewHolder {

        val layout = when (viewType) {
            TYPE_RESTAURANT -> R.layout.dish
            else -> throw IllegalArgumentException("Invalid type")
        }

        val view = LayoutInflater
            .from(parent.context)
            .inflate(layout, parent, false)

        scoutingViewHolder = RestaurantViewHolder(this, view)

        return scoutingViewHolder
    }


    override fun onBindViewHolder(
        holder: RestaurantViewHolder,
        position: Int
    ) {
        holder.bind(adapterData[position])
    }

    override fun getItemCount(): Int = adapterData.size

    override fun getItemViewType(position: Int): Int {
        return TYPE_RESTAURANT
    }

    fun getData(): List<FoodModel> {
        return adapterData;
    }

    fun setData(data: List<FoodModel>) {
        adapterData.apply {
            clear()
            addAll(data)
        }

        notifyDataSetChanged()
    }

    companion object {
        private const val TYPE_RESTAURANT = 0
    }
}