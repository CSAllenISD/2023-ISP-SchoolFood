package com.schoolfood.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.schoolfood.MainActivity
import com.schoolfood.databinding.FragmentHomeBinding
import com.schoolfood.datamodel.home.RestaurantsAdapter
import com.schoolfood.sources.Restaurants

class HomeFragment : Fragment() {

    private val dataAdapter: RestaurantsAdapter by lazy {
        RestaurantsAdapter(this, activity as MainActivity)
    }

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        dataAdapter.setData(Restaurants.getRestaurants())
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