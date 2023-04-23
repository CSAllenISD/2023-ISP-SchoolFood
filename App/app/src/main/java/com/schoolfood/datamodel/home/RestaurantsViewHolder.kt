package com.schoolfood.datamodel.home

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.schoolfood.R
import com.schoolfood.ui.home.HomeFragmentDirections
import com.schoolfood.ui.restaurants.RestaurantFragmentDirections


class RestaurantsViewHolder (restaurantsAdapter: RestaurantsAdapter, itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val adapter : RestaurantsAdapter = restaurantsAdapter;

    fun bind(dataModel: RestaurantModel) {
        val label : TextView = itemView.findViewById(R.id.label)
        label.text = dataModel.name

        val image : ImageView = itemView.findViewById(R.id.cover_image)
        image.setImageResource(dataModel.image)

        itemView.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToNavigationSubway(dataModel.name)
            findNavController(adapter.fragment).navigate(action)
        }
    }
}