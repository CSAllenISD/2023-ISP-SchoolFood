package com.schoolfood.ui.customize

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
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.slider.Slider
import com.schoolfood.MainActivity
import com.schoolfood.R
import com.schoolfood.databinding.FragmentCustomizeBinding
import com.schoolfood.datamodel.customize.CustomizeAdapter
import com.schoolfood.datamodel.customize.CustomizeModel
import com.schoolfood.sources.Customizations
import com.schoolfood.sources.Foods
import com.schoolfood.sources.Restaurants

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