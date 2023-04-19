package com.schoolfood.ui.customize

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
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
        val subwayViewModel =
            ViewModelProvider(this)[CustomizeViewModel::class.java]

        _binding = FragmentCustomizeBinding.inflate(inflater, container, false)

        val backBtn : ImageButton = binding.backBtn
        backBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        dataAdapter.setData(getData())
        val mainView = binding.customizeRecyclerView;
        mainView.apply {
            layoutManager = LinearLayoutManager(context)
            hasFixedSize()
            this.adapter = dataAdapter
        }

        return binding.root
    }

    private fun getData(): List<CustomizeModel> = listOf(
        CustomizeModel.SliderFree(
            name = "Lettuce",
            options = listOf("None", "Light", "Regular", "Extra"),
            chosenOption = "None",
            imagePath = R.drawable.lettuce
        ),
        CustomizeModel.SliderFree(
            name = "Lettuce",
            options = listOf("None", "Light", "Regular", "Extra"),
            chosenOption = "None",
            imagePath = R.drawable.lettuce
        )
    )

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}