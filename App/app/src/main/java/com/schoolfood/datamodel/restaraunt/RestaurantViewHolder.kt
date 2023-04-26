package com.schoolfood.datamodel.restaraunt

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.schoolfood.R
import com.schoolfood.ui.home.HomeFragmentDirections
import com.schoolfood.ui.restaurants.RestaurantFragmentDirections


class RestaurantViewHolder (restaurantAdapter: RestaurantAdapter, itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val adapter : RestaurantAdapter = restaurantAdapter;

    fun bind(dataModel: FoodModel) {
        val label : TextView = itemView.findViewById(R.id.dish_name)
        label.text = dataModel.name

        val costLabel : TextView = itemView.findViewById(R.id.dish_cost)
        costLabel.text = "$" + String.format("%.2f", dataModel.price)

        val image : ImageView = itemView.findViewById(R.id.dish_image)
        image.setImageResource(dataModel.image)
        image.contentDescription = dataModel.name + " Image"

        itemView.setOnClickListener {
            val action = RestaurantFragmentDirections.actionNavigationdSubwayToNavigationCustomize(dataModel.name)
            findNavController(adapter.fragment).navigate(action)
        }
    }
}