package com.example.bangkitcapstone.ui.feature.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.bangkitcapstone.R
import com.example.bangkitcapstone.core.ui.SectionsPagerAdapter
import com.example.bangkitcapstone.databinding.FragmentReportBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ReportFragment : Fragment() {

    private var _binding: FragmentReportBinding? = null
    private val binding get() = _binding as FragmentReportBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        _binding = FragmentReportBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = SectionsPagerAdapter(requireActivity() as AppCompatActivity)
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = adapter
        val tabs: TabLayout = binding.tabs
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        (activity as AppCompatActivity?)?.supportActionBar?.elevation = 0f
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onStop() {
        super.onStop()
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.accepted,
            R.string.rejected
        )
    }
}