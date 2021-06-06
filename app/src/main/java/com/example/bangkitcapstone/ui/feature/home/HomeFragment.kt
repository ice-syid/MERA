package com.example.bangkitcapstone.ui.feature.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.bangkitcapstone.core.model.Case
import com.example.bangkitcapstone.core.ui.CaseAdapter
import com.example.bangkitcapstone.databinding.FragmentHomeBinding
import com.google.firebase.firestore.*

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding as FragmentHomeBinding

    private lateinit var caseList: ArrayList<Case>
    private lateinit var db: FirebaseFirestore
    private lateinit var caseAdapter: CaseAdapter

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
        with(binding) {
            Glide.with(view.context)
                .load("https://static.wikia.nocookie.net/solo-leveling/images/5/59/Jin-Woo_Profile.png/revision/latest/scale-to-width-down/340?cb=20200208070835")
                .into(imgProfile)
        }

        caseList = arrayListOf()
        caseAdapter = CaseAdapter(caseList)
        with(binding.rvCase) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = caseAdapter
        }
        EventChangeListener()

        binding.btnNewReport.setOnClickListener {
            val actionCreateNewReport =
                HomeFragmentDirections.actionHomeFragmentToCaseFormFragment()
            view.findNavController().navigate(actionCreateNewReport)
        }
    }

    private fun EventChangeListener() {
        db = FirebaseFirestore.getInstance()
        db.collection("cases")
            .orderBy("date_case", Query.Direction.DESCENDING)
            .limit(5)
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                    if (error != null) {
                        Log.w("HomeFragment", "Listen failed.", error)
                        return
                    }
                    for (dc: DocumentChange in value?.documentChanges!!) {
                        if (dc.type == DocumentChange.Type.ADDED) {
                            caseList.add(dc.document.toObject(Case::class.java))
                        }
                    }
                    caseAdapter.notifyDataSetChanged()
                }
            })
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