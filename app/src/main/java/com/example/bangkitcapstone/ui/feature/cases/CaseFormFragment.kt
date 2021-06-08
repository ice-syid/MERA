package com.example.bangkitcapstone.ui.feature.cases

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.bangkitcapstone.R
import com.example.bangkitcapstone.core.model.Case
import com.example.bangkitcapstone.databinding.FragmentCaseFormBinding
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class CaseFormFragment : Fragment() {

    private var _binding: FragmentCaseFormBinding? = null
    private val binding get() = _binding as FragmentCaseFormBinding
    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCaseFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupUI()
    }

    private fun setupUI() {
        val options = arrayOf(
            "Select Category",
            "Pemerkosaan",
            "Perdagangan/Prostitusi",
            "KDRT",
            "Bully",
            "Body Shaming",
            "Rasisme"
        )

        val calendar = Calendar.getInstance()
        val datePicker = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            setCalendar(calendar)
        }

        binding.ibDate.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                datePicker,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        binding.spinnerCategory.adapter =
            ArrayAdapter(requireContext(), R.layout.spinner_item, options)

        binding.btnCreateReport.setOnClickListener {
            val case = getDataFromUser()
            if (case != null) {
                createCase(case)
                Toast.makeText(requireContext(), "Successful", Toast.LENGTH_SHORT).show()
                val actionCreateNewReport =
                    CaseFormFragmentDirections.actionCaseFormFragmentToCaseFeedbackFragment()
                view?.findNavController()?.navigate(actionCreateNewReport)
            } else {
                Toast.makeText(requireContext(), "Failed", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun setCalendar(calendar: Calendar) {
        val format = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(format, Locale.UK)
        binding.edtDate.text = sdf.format((calendar.time)).toString()
    }

    @SuppressLint("SimpleDateFormat")
    private fun getDataFromUser(): Case? {
        val pattern = "dd-MM-yyyy HH:mm:ss"
        val simpleDateFormat = SimpleDateFormat(pattern)
        val time = simpleDateFormat.format(Date())

        val sharedPreferences =
            activity?.getSharedPreferences("USER", Context.MODE_PRIVATE)

        val email_user = sharedPreferences?.getString("email", "").toString()
        val name = binding.edtName.text.toString()
        val address = binding.edtAddress.text.toString()
        val rbId = binding.rgGender.checkedRadioButtonId
        val rb: View = binding.rgGender.findViewById(rbId)
        val idx = binding.rgGender.indexOfChild(rb)
        val gender = when (idx) {
            0 -> "Male"
            1 -> "Female"
            else -> ""
        }
        val date_birth = binding.edtDate.text.toString()
        val type = binding.spinnerCategory.selectedItemPosition.toString()
        val category = ""
        val description = binding.edtDescription.text.toString()
        val date = time
        val status = "Accepted"
        val feedback = ""

        if (name != "" && address != "" && gender != "" && date_birth != "" && type != "0" && description != "") {
            return Case(
                email_user,
                name,
                address,
                gender,
                date_birth,
                type,
                category,
                description,
                date,
                status,
                feedback
            )
        } else {
            return null
        }
    }

    private fun createCase(case: Case) {
        db.collection("cases")
            .add(case)
            .addOnSuccessListener { documentReference ->
                Log.d(
                    "CaseFormFragment",
                    "DocumentSnapshot written with ID: ${documentReference.id}"
                )
            }
            .addOnFailureListener { e ->
                Log.w("CaseFormFragment", "Error adding document", e)
            }
    }
}