package com.example.newschoco.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.example.newschoco.data.db.AppDataBase
import com.example.newschoco.data.db.AppDataBase_Impl
import com.example.newschoco.data.model.headline.Articles
import com.example.newschoco.data.pagination.HeadLinePag
import com.example.newschoco.data.remote.NewsService

interface NewsRepository{
    @ExperimentalPagingApi
    fun getPagingResult():LiveData<PagingData<Articles>>
}

class NewsRepositoryImpl(private val network: NewsService,private val db : AppDataBase) : NewsRepository{

    @ExperimentalPagingApi
    override fun getPagingResult(): LiveData<PagingData<Articles>> {
       val pagingSourceFactory = {db.getContentDao().getAll()}
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE,enablePlaceholders = false),
            remoteMediator = HeadLinePag(network,db),
            pagingSourceFactory = pagingSourceFactory
        ).liveData
    }

    companion object{
        const val  PAGE_SIZE = 90
    }
}