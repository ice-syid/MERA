package com.example.bangkitcapstone.ui.feature.cases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bangkitcapstone.core.ui.CaseAdapter
import com.example.bangkitcapstone.databinding.FragmentCaseBinding

class CaseFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var caseAdapter: CaseAdapter
    private var _binding: FragmentCaseBinding? = null
    private val binding get() = _binding as FragmentCaseBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCaseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.rvCase
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        val index = arguments?.getInt(ARG_SECTION_NUMBER, 0) ?: 1
        when (index) {
//            1 -> setCase()
//            2 -> setCase()
        }
    }

//    private fun setCase() {
//        caseAdapter = CaseAdapter()
//        recyclerView.adapter = caseAdapter
//    }

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(index: Int) =
            CaseFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, index)
                }
            }
    }
}