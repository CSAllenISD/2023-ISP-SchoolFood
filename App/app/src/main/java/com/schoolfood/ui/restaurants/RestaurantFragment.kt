package com.schoolfood.ui.restaurants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.schoolfood.MainActivity
import com.schoolfood.databinding.FragmentRestaurantBinding
import com.schoolfood.datamodel.home.RestaurantModel
import com.schoolfood.datamodel.home.RestaurantsAdapter
import com.schoolfood.datamodel.restaraunt.FoodModel
import com.schoolfood.datamodel.restaraunt.RestaurantAdapter
import com.schoolfood.sources.Foods
import com.schoolfood.sources.Restaurants

class RestaurantFragment : Fragment() {

    private val dataAdapter: RestaurantAdapter by lazy {
        RestaurantAdapter(this, activity as MainActivity)
    }

    private var _binding: FragmentRestaurantBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRestaurantBinding.inflate(inflater, container, false)

        val restaurantName = arguments?.getString("restaurantName", "None");
        if (restaurantName == "None" || restaurantName.isNullOrEmpty()) {
            findNavController().popBackStack()
            val toast = Toast.makeText(context, "Invalid Restaurant!", Toast.LENGTH_SHORT)
            toast.show()
            return binding.root
        }
        val headerText : TextView = binding.homeHeaderText
        headerText.text = restaurantName

        val restaurant = Restaurants.getRestaurants().find { it.name == restaurantName }
            ?: return binding.root

        val image : ImageView = binding.restaurantImage
        image.setImageResource(restaurant.image)
        image.contentDescription = "$restaurantName Logo"

        val backBtn : ImageButton = binding.backBtn
        backBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        val foods = Foods.getFoods().filter { it.restaurant == restaurantName }
        dataAdapter.setData(foods)
        val mainView = binding.customizeRecyclerView;
        mainView.apply {
            layoutManager = LinearLayoutManager(context)
            hasFixedSize()
            this.adapter = dataAdapter
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}