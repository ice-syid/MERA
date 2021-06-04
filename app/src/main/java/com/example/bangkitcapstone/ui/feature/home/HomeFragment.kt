package com.example.bangkitcapstone.ui.feature.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bangkitcapstone.core.ui.CaseAdapter
import com.example.bangkitcapstone.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding as FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val sharedPreferences =
            activity?.getSharedPreferences("USER", Context.MODE_PRIVATE)
        val name = sharedPreferences?.getString("name", "").toString()
        val email = sharedPreferences?.getString("email", "").toString()

        binding.tvName.text = name
        binding.tvEmail.text = email

        if (activity != null) {
            val caseAdapter = CaseAdapter()
            with(binding.rvCase) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = caseAdapter
            }
        }

        binding.btnNewReport.setOnClickListener {
            val actionCreateNewReport =
                HomeFragmentDirections.actionHomeFragmentToCaseFormFragment()
            view.findNavController().navigate(actionCreateNewReport)
        }
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onStop() {
        super.onStop()
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
    }
}