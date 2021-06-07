package com.example.bangkitcapstone.ui.main.register

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.bangkitcapstone.core.model.User
import com.example.bangkitcapstone.databinding.FragmentRegisterBinding
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding as FragmentRegisterBinding
    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupUI()
    }

    private fun setupUI() {
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

        binding.btnRegister.setOnClickListener {
            val user = getDataFromUser()
            if (user != null) {
                createUser(user)
                Toast.makeText(requireContext(), "Successful", Toast.LENGTH_SHORT).show()
                val actionRegisterLogin =
                    RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
                view?.findNavController()?.navigate(actionRegisterLogin)
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

    private fun getDataFromUser(): User? {
        val name = binding.edtName.text.toString()
        val address = binding.edtAddress.text.toString()
        val gender = when (binding.rgGender.checkedRadioButtonId) {
            2131231064 -> "Male"
            2131231063 -> "Female"
            else -> ""
        }
        val date_birth = binding.edtDate.text.toString()
        val email = binding.edtEmail.text.toString()
        val password = binding.edtPassword.text.toString()
        val phone = binding.edtPhone.text.toString()

        return if (name != "" && address != "" && gender != "" && date_birth != "" && email != "" && password != "" && phone != "") {
            User(name, address, gender, date_birth, email, password, phone)
        } else {
            null
        }
    }

    private fun createUser(user: User) {
        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(
                    "RegisterFragment",
                    "DocumentSnapshot written with ID: ${documentReference.id}"
                )
            }
            .addOnFailureListener { e ->
                Log.w("RegisterFragment", "Error adding document", e)
            }
    }
}