package com.example.newschoco.ui.bottomNavigation.home.headlines

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.paging.ExperimentalPagingApi
import com.example.newschoco.R
import com.example.newschoco.databinding.FragmentHeadlinesBinding
import com.example.newschoco.databinding.FragmentHomeLayoutBinding
import com.example.newschoco.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HeadlinesFragment:Fragment(R.layout.fragment_headlines) {

    private val binding by viewBinding(FragmentHeadlinesBinding::bind)
    private val vm by viewModel<HeadLineVieModel>()

    private val adapter by lazy { HeadLineRecyclerAdapter() }


    @ExperimentalPagingApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        setupVM()
    }

    @ExperimentalPagingApi
    private fun setupVM() {
        vm.getPagingMarsData().observe(
            viewLifecycleOwner, Observer {
                adapter.submitData(lifecycle,it)
            }
        )
    }
}