package com.schoolfood.ui.restaurants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.schoolfood.databinding.FragmentResterauntBinding
import com.schoolfood.ui.home.HomeFragmentDirections

class RestaurantsFragment : Fragment() {

    private var _binding: FragmentResterauntBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val restaurantsViewModel =
            ViewModelProvider(this)[RestaurantsViewModel::class.java]

        _binding = FragmentResterauntBinding.inflate(inflater, container, false)

        val headerText : TextView = binding.homeHeaderText;
        headerText.text = restaurantsViewModel.title.value

        val backBtn : ImageButton = binding.backBtn
        backBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        val hamSandwich = binding.hamSandwich
        hamSandwich.setOnClickListener {
            val action = RestaurantsFragmentDirections.actionNavigationdSubwayToNavigationCustomize()
            findNavController().navigate(action)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}