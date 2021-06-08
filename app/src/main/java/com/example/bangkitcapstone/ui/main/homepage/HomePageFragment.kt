package com.example.bangkitcapstone.ui.main.homepage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.bangkitcapstone.databinding.FragmentHomePageBinding
import com.example.bangkitcapstone.ui.feature.HomeActivity

class HomePageFragment : Fragment() {

    private var _binding: FragmentHomePageBinding? = null
    private val binding get() = _binding as FragmentHomePageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomePageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val sharedPreferences =
            activity?.getSharedPreferences("USER", Context.MODE_PRIVATE)
        val email = sharedPreferences?.getString("email", "").toString()

        if (email != "") {
            val intent = Intent(activity, HomeActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

        binding.btnLogin.setOnClickListener {
            val actionLogin =
                HomePageFragmentDirections.actionHomePageFragmentToLoginFragment()
            view.findNavController().navigate(actionLogin)
        }

        binding.btnRegister.setOnClickListener {
            val actionRegister =
                HomePageFragmentDirections.actionHomePageFragmentToRegisterFragment()
            view.findNavController().navigate(actionRegister)
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