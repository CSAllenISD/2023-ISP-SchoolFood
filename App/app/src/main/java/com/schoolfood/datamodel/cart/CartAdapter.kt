package com.schoolfood.datamodel.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.schoolfood.MainActivity
import com.schoolfood.R

class CartAdapter(fragment: Fragment, activity: MainActivity) : RecyclerView.Adapter<CartViewHolder>() {

    val fragment = fragment
    val context = activity
    private val adapterData = mutableListOf<CartFoodModel>()
    private lateinit var scoutingViewHolder: CartViewHolder;

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CartViewHolder {

        val layout = when (viewType) {
            TYPE_RESTAURANT -> R.layout.dish_cart
            else -> throw IllegalArgumentException("Invalid type")
        }

        val view = LayoutInflater
            .from(parent.context)
            .inflate(layout, parent, false)

        scoutingViewHolder = CartViewHolder(this, view)

        return scoutingViewHolder
    }


    override fun onBindViewHolder(
        holder: CartViewHolder,
        position: Int
    ) {
        holder.bind(adapterData[position])
    }

    override fun getItemCount(): Int = adapterData.size

    override fun getItemViewType(position: Int): Int {
        return TYPE_RESTAURANT
    }

    fun getData(): List<CartFoodModel> {
        return adapterData;
    }

    fun setData(data: List<CartFoodModel>) {
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