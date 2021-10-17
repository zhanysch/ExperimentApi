package com.example.newschoco.ui.bottomNavigation.home.all

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagedList
import androidx.paging.PagingData
import com.example.newschoco.data.model.everything.EverythingArticles
import com.example.newschoco.data.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector

class AllNewsViewModel(private val repository : NewsRepository): ViewModel() {


    lateinit var postsLiveData: LiveData<PagedList<EverythingArticles>>

    @ExperimentalPagingApi
    fun getPagingData(query : String) : Flow<PagingData<EverythingArticles>> {
        return repository.getPagingAllNews(query)
    }

}