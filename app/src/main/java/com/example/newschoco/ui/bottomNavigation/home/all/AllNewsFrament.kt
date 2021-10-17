package com.example.newschoco.ui.bottomNavigation.home.all

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import com.example.newschoco.R
import com.example.newschoco.databinding.FragmentAllBinding
import com.example.newschoco.databinding.FragmentHomeLayoutBinding
import com.example.newschoco.ui.bottomNavigation.home.headlines.HeadLineVieModel
import com.example.newschoco.utils.viewBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.app.Activity
import android.content.Context

import android.view.View.OnFocusChangeListener
import android.view.inputmethod.InputMethodManager
import androidx.navigation.fragment.findNavController
import com.example.newschoco.data.model.everything.EverythingArticles
import com.example.newschoco.data.model.headline.Articles
import com.example.newschoco.ui.bottomNavigation.home.HomeFragment
import com.example.newschoco.ui.bottomNavigation.home.HomeFragmentDirections


class AllNewsFrament : Fragment(R.layout.fragment_all) {

    private val binding by viewBinding(FragmentAllBinding::bind)
    private val vm by viewModel<AllNewsViewModel>()
    private var searchJob : Job? = null
    private val adapter by lazy { AllNewsAdapter(){
            item: EverythingArticles? ->
        navigateToDetails(item)
    } }

    @ExperimentalPagingApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycler.adapter = adapter
        setupRecycler()
        setupListeners()

    }

    private fun navigateToDetails(item : EverythingArticles?){
      val destination = HomeFragmentDirections.actionHomeFragmentToEverythingDetailsFragment(item)
        findNavController().navigate(destination)
    }

    private fun setupRecycler() {
        binding.recycler.adapter = adapter
    }

    @ExperimentalPagingApi
    private fun setupListeners() {
        binding.etSearch.setOnEditorActionListener { v, actionId, Event ->
            if (actionId ==  EditorInfo.IME_ACTION_SEARCH){
                updateSearchRepository()

                true
            }else
                false
        }

        adapter.addLoadStateListener {loadState ->
            binding.recycler.isVisible = loadState.source.refresh is LoadState.NotLoading
            binding.progesBar.isVisible  = loadState.source.refresh is LoadState.Loading
            binding.btnRetry.isVisible = loadState.source.refresh is LoadState.Error
        }
        binding.btnRetry.setOnClickListener {
            adapter.retry()
        }
    }

    @ExperimentalPagingApi
    fun updateSearchRepository(){
        binding.etSearch.text?.trim().let {
            if (it?.isNotEmpty()!!)
                search(it.toString())
        }
    }

    @ExperimentalPagingApi
    private fun search(query: String){
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            vm.getPagingData(query).collectLatest {
                adapter.submitData(it)
            }
        }
    }
}