package com.schoolfood.ui.subway

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.schoolfood.databinding.FragmentHomeBinding
import com.schoolfood.databinding.FragmentSubwayBinding

class SubwayFragment : Fragment() {

    private var _binding: FragmentSubwayBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val subwayViewModel =
            ViewModelProvider(this)[SubwayViewModel::class.java]

        _binding = FragmentSubwayBinding.inflate(inflater, container, false)

        val backBtn : ImageButton = binding.backBtn
        backBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}