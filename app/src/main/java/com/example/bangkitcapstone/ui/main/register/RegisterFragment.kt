package com.example.bangkitcapstone.ui.main.register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.bangkitcapstone.core.model.User
import com.example.bangkitcapstone.databinding.FragmentRegisterBinding
import com.google.firebase.firestore.FirebaseFirestore


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
        binding.btnRegister.setOnClickListener {
            createUser()
            val actionRegisterLogin =
                RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            view.findNavController().navigate(actionRegisterLogin)
        }
    }

    private fun createUser() {
        val name = binding.edtName.text.toString()
        val address = binding.edtAddress.text.toString()
        val gender = when (binding.rgGender.checkedRadioButtonId) {
            2131231064 -> "Male"
            2131231063 -> "Female"
            else -> "Unknown"
        }
        val date_birth = binding.edtDate.text.toString()
        val email = binding.edtEmail.text.toString()
        val password = binding.edtPassword.text.toString()
        val phone = binding.edtPhone.text.toString()

        val user = User(1, name, address, gender, date_birth, email, password, phone)

        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d("syid", "DocumentSnapshot written with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("syid", "Error adding document", e)
            }
    }
}