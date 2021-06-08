package com.example.bangkitcapstone.ui.feature.cases

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bangkitcapstone.core.model.Case
import com.example.bangkitcapstone.core.ui.ReportCaseAdapter
import com.example.bangkitcapstone.databinding.FragmentCaseBinding
import com.google.firebase.firestore.*

class CaseFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var caseListAccepted: ArrayList<Case>
    private lateinit var caseListRejected: ArrayList<Case>
    private lateinit var db: FirebaseFirestore
    private lateinit var reportCaseAdapterAccepted: ReportCaseAdapter
    private lateinit var reportCaseAdapterRejected: ReportCaseAdapter
    private var _binding: FragmentCaseBinding? = null
    private val binding get() = _binding as FragmentCaseBinding
    private var email: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val sharedPreferences =
            activity?.getSharedPreferences("USER", Context.MODE_PRIVATE)
        email = sharedPreferences?.getString("email", "").toString()

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
            1 -> {
                caseListAccepted = arrayListOf()
                reportCaseAdapterAccepted = ReportCaseAdapter(caseListAccepted)
                recyclerView.adapter = reportCaseAdapterAccepted
                EventChangeListener("Accepted", caseListAccepted, reportCaseAdapterAccepted)
            }
            2 -> {
                caseListRejected = arrayListOf()
                reportCaseAdapterRejected = ReportCaseAdapter(caseListRejected)
                recyclerView.adapter = reportCaseAdapterRejected
                EventChangeListener("Rejected", caseListRejected, reportCaseAdapterRejected)
            }
        }
    }

    private fun EventChangeListener(
        status: String,
        list: ArrayList<Case>,
        adapterReport: ReportCaseAdapter
    ) {
        db = FirebaseFirestore.getInstance()
        db.collection("cases")
            .whereEqualTo("status", status)
            .whereEqualTo("email_user", email)
            .orderBy("date_case", Query.Direction.DESCENDING)
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                    if (error != null) {
                        Log.w("HomeFragment", "Listen failed.", error)
                        return
                    }
                    for (dc: DocumentChange in value?.documentChanges!!) {
                        if (dc.type == DocumentChange.Type.ADDED) {
                            list.add(dc.document.toObject(Case::class.java))
                        }
                    }
                    adapterReport.notifyDataSetChanged()
                }
            })
    }

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