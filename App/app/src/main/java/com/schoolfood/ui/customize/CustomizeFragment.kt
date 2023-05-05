package com.schoolfood.ui.customize

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.slider.Slider
import com.schoolfood.MainActivity
import com.schoolfood.R
import com.schoolfood.databinding.FragmentCustomizeBinding
import com.schoolfood.datamodel.customize.CustomizeAdapter
import com.schoolfood.datamodel.customize.CustomizeModel
import com.schoolfood.datamodel.restaraunt.FoodModel
import com.schoolfood.sources.Customizations
import com.schoolfood.sources.Foods
import com.schoolfood.sources.Restaurants
import java.io.File
import java.util.*

class CustomizeFragment : Fragment() {

    private val dataAdapter: CustomizeAdapter by lazy {
        CustomizeAdapter(activity as MainActivity)
    }

    private var _binding: FragmentCustomizeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCustomizeBinding.inflate(inflater, container, false)

        val foodName = arguments?.getString("foodName", "None");
        if (foodName == "None" || foodName.isNullOrEmpty()) {
            findNavController().popBackStack()
            val toast = Toast.makeText(context, "Invalid Dish!", Toast.LENGTH_SHORT)
            toast.show()
            return binding.root
        }

        val headerText : TextView = binding.homeHeaderText
        headerText.text = foodName

        val dish = Foods.getFoods().find { it.name == foodName }
            ?: return binding.root

        val image : ImageView = binding.customizeHeaderImage
        image.setImageResource(dish.image)
        image.contentDescription = "$foodName Image"

        val backBtn : ImageButton = binding.backBtn
        backBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        val addToCartBtn : Button = binding.confirm
        addToCartBtn.setOnClickListener {
            var price = dish.price.toDouble()
            var customizations = ""
            dataAdapter.getData().forEach {
                when (it) {
                    is CustomizeModel.Toggle -> {
                        if(it.chosen) {
                            price += it.selectedPrice
                            customizations += it.name + ":1,"
                        }
                    }
                    is CustomizeModel.Slider -> {
                        price += it.priceOptions[it.chosenIndex]
                        customizations += it.name + ":" + it.chosenOption + ","
                    }
                    is CustomizeModel.DropdownFree -> {
                        customizations += it.name + ":" + it.options[it.chosenIndex] + ","
                    }
                    is CustomizeModel.SliderFree -> {
                        customizations += it.name + ":" + it.chosenOption + ","
                    }
                    else -> {}
                }
            }
            customizations = customizations.dropLast(1)

            val path = context?.filesDir
            val file = File(path, "cart.txt")
            if (!file.exists()) file.createNewFile()
            var foodID = UUID.randomUUID()
            file.appendText("$foodID,$foodName,$price,$customizations\n")


            Toast.makeText(context,
                "Added $foodName to your Cart!", Toast.LENGTH_LONG).show()

            findNavController().popBackStack()
        }

        dataAdapter.setData(Customizations.getCustomizations(foodName))
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