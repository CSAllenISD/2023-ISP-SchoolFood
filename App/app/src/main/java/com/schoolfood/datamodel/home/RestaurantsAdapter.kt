package com.schoolfood.datamodel.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.schoolfood.MainActivity
import com.schoolfood.R

class RestaurantsAdapter(fragment: Fragment, activity: MainActivity) : RecyclerView.Adapter<RestaurantsViewHolder>() {

    val fragment = fragment
    val context = activity
    private val adapterData = mutableListOf<RestaurantModel>()
    private lateinit var scoutingViewHolder: RestaurantsViewHolder;

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RestaurantsViewHolder {

        val layout = when (viewType) {
            TYPE_RESTAURANT -> R.layout.restaurant
            else -> throw IllegalArgumentException("Invalid type")
        }

        val view = LayoutInflater
            .from(parent.context)
            .inflate(layout, parent, false)

        scoutingViewHolder = RestaurantsViewHolder(this, view)

        return scoutingViewHolder
    }


    override fun onBindViewHolder(
        holder: RestaurantsViewHolder,
        position: Int
    ) {
        holder.bind(adapterData[position])
    }

    override fun getItemCount(): Int = adapterData.size

    override fun getItemViewType(position: Int): Int {
        return TYPE_RESTAURANT
    }

    fun getData(): List<RestaurantModel> {
        return adapterData;
    }

    fun setData(data: List<RestaurantModel>) {
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