package com.example.bangkitcapstone.ui.feature.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.bangkitcapstone.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding as FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val sharedPreferences =
            activity?.getSharedPreferences("USER", Context.MODE_PRIVATE)
        val name = sharedPreferences?.getString("name", "").toString()
        val address = sharedPreferences?.getString("address", "").toString()
        val gender = sharedPreferences?.getString("gender", "").toString()
        val date_birth = sharedPreferences?.getString("date_birth", "").toString()
        val email = sharedPreferences?.getString("email", "").toString()
        val phone = sharedPreferences?.getString("phone", "").toString()

        binding.tvNameValue.text = name
        binding.tvAddressValue.text = address
        binding.tvGenderValue.text = gender
        binding.tvDateValue.text = date_birth
        binding.tvEmailValue.text = email
        binding.tvPhoneValue.text = phone
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