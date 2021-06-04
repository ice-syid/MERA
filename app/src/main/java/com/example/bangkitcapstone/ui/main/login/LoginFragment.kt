package com.example.bangkitcapstone.ui.main.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.bangkitcapstone.core.model.User
import com.example.bangkitcapstone.core.utils.MyCallback
import com.example.bangkitcapstone.databinding.FragmentLoginBinding
import com.example.bangkitcapstone.ui.feature.HomeActivity
import com.google.firebase.firestore.FirebaseFirestore

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding as FragmentLoginBinding
    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnLogin.setOnClickListener {
            loginUser(object : MyCallback {
                override fun onCallbackUser(value: User) {
                    Toast.makeText(requireContext(), "Successful", Toast.LENGTH_SHORT).show()

                    val sharedPreferences =
                        activity?.getSharedPreferences("USER", Context.MODE_PRIVATE)
                    val editor = sharedPreferences?.edit()
                    editor?.putString("name", value.name)
                    editor?.putString("address", value.address)
                    editor?.putString("gender", value.gender)
                    editor?.putString("date_birth", value.date_birth)
                    editor?.putString("email", value.email)
                    editor?.putString("password", value.password)
                    editor?.putString("phone", value.phone)
                    editor?.apply()

                    val intent = Intent(activity, HomeActivity::class.java)
                    startActivity(intent)
                    activity?.finish()
                }
            })
        }
    }

    private fun loginUser(callback: MyCallback) {
        val emailUser = binding.edtEmail.text.toString()
        val passwordUser = binding.edtPassword.text.toString()

        db.collection("users")
            .whereEqualTo("email", emailUser)
            .whereEqualTo("password", passwordUser)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d("LoginFragment", "${document.id} => ${document.data}")
                    val name = document.data["name"].toString()
                    val address = document.data["address"].toString()
                    val gender = document.data["gender"].toString()
                    val date_birth = document.data["date_birth"].toString()
                    val email = document.data["email"].toString()
                    val password = document.data["password"].toString()
                    val phone = document.data["phone"].toString()
                    val user = User(name, address, gender, date_birth, email, password, phone)
                    callback.onCallbackUser(user)
                }
            }
            .addOnFailureListener { exception ->
                Log.w("LoginFragment", "Error getting documents: ", exception)
            }
    }
}