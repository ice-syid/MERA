package com.example.bangkitcapstone.ui.main.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
            loginUser()
            val intent = Intent(activity, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loginUser() {
        val email = binding.edtEmail.text.toString()
        val password = binding.edtPassword.text.toString()

        db.collection("users")
            .whereEqualTo("email", email)
            .whereEqualTo("password", password)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d("LoginFragment", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w("LoginFragment", "Error getting documents: ", exception)
            }
    }
}