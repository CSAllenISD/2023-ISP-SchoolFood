package com.schoolfood.ui.previousordered

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.schoolfood.databinding.FragmentPreviousorderedBinding

class PreviousOrderedFragment : Fragment() {

    private var _binding: FragmentPreviousorderedBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val previousOrderedViewModel =
            ViewModelProvider(this)[PreviousOrderedViewModel::class.java]

        _binding = FragmentPreviousorderedBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textPrevOrdered
        previousOrderedViewModel.text.observeForever {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
