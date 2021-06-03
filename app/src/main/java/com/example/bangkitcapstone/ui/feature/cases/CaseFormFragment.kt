package com.example.bangkitcapstone.ui.feature.cases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.bangkitcapstone.R
import com.example.bangkitcapstone.databinding.FragmentCaseFormBinding

class CaseFormFragment : Fragment() {

    private var _binding: FragmentCaseFormBinding? = null
    private val binding get() = _binding as FragmentCaseFormBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCaseFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val options = arrayOf(
            "Select Category",
            "Pemerkosaan",
            "Perdagangan/Prostitusi",
            "KDRT",
            "Bully",
            "Body Shaming",
            "Rasisme"
        )
        binding.spinnerCategory.adapter =
            ArrayAdapter<String>(requireContext(), R.layout.spinner_item, options)
    }
}