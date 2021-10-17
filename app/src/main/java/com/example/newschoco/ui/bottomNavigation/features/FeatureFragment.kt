package com.example.newschoco.ui.bottomNavigation.features

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.newschoco.R
import com.example.newschoco.databinding.FragmentFeaturesLayoutBinding
import com.example.newschoco.databinding.FragmentHeadlinesDetailsBinding
import com.example.newschoco.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FeatureFragment :Fragment(R.layout.fragment_features_layout) {

    private val binding by viewBinding(FragmentFeaturesLayoutBinding::bind)
    private val vm by viewModel<FeatureViewModel>()

    private val adapter by lazy { HeadLineFeatureAdapterAdapter(vm) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup() {
        binding.recyclerView.adapter =adapter
        vm.getSavedFeatureNewsHeadLine().observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }
}