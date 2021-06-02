package com.example.bangkitcapstone.core.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.bangkitcapstone.ui.feature.cases.CaseFragment

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return CaseFragment.newInstance(position + 1)
    }
}