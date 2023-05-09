package com.schoolfood.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.schoolfood.databinding.FragmentProfileBinding
import java.io.File
import java.io.FileInputStream

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        val path = context?.filesDir
        val file = File(path, "balance.txt")
        if (!file.exists()) file.createNewFile()
        val balance = FileInputStream(file).bufferedReader().use { it.readLines() }
        binding.dishCost.text =  "$" + String.format("%.2f", balance[0].toFloat())

        val file2 = File(path, "timesOrdered.txt")
        if (!file2.exists()) file2.createNewFile()
        val timesOrdered = FileInputStream(file2).bufferedReader().use { it.readLines() }
        if (timesOrdered.isEmpty()) {
            binding.profilerTimesAmt.text = "0"
        } else {
            binding.profilerTimesAmt.text = timesOrdered[0].toInt().toString()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}