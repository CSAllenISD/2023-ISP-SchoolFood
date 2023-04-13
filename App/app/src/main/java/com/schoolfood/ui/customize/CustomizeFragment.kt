package com.schoolfood.ui.customize

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.slider.Slider
import com.schoolfood.databinding.FragmentCustomizeBinding
import com.schoolfood.databinding.FragmentHomeBinding

class CustomizeFragment : Fragment() {

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

        val sliderValues = listOf("None", "Light", "Regular", "Extra");

        val slider: Slider = binding.lettuceSlider
        slider.setLabelFormatter { value: Float ->
            sliderValues[value.toInt()]
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}