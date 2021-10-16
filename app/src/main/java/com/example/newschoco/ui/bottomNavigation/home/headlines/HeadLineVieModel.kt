package com.example.newschoco.ui.bottomNavigation.home.headlines

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import com.example.newschoco.data.db.AppDataBase
import com.example.newschoco.data.model.headline.Articles
import com.example.newschoco.data.repository.NewsRepository

class HeadLineVieModel(private val repository: NewsRepository, private val db:AppDataBase):ViewModel() {

    @ExperimentalPagingApi
    fun getPagingMarsData():LiveData<PagingData<Articles>>{
        return repository.getPagingResult()
    }
}