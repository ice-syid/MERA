package com.example.bangkitcapstone.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bangkitcapstone.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}