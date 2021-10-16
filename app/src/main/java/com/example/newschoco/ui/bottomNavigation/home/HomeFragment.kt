package com.example.newschoco.ui.bottomNavigation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.newschoco.R
import com.example.newschoco.databinding.ActivityMainBinding.bind
import com.example.newschoco.databinding.FragmentFeaturesLayoutBinding
import com.example.newschoco.databinding.FragmentHomeLayoutBinding
import com.example.newschoco.ui.bottomNavigation.home.all.AllNewsFrament
import com.example.newschoco.ui.bottomNavigation.home.headlines.HeadlinesFragment
import com.example.newschoco.utils.viewBinding

class HomeFragment: Fragment(R.layout.fragment_home_layout) {

    private val binding by viewBinding(FragmentHomeLayoutBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()
    }

    private fun setupViewPager() {
        val adapter = PagerAdapter(childFragmentManager)
        binding.viewPager.adapter = adapter
        adapter.addFragments(HeadlinesFragment(),"Headlines")
        adapter.addFragments(AllNewsFrament(),"AllNews")
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }
}