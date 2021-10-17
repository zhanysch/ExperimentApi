package com.example.newschoco.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.example.newschoco.data.db.AppDataBase
import com.example.newschoco.data.db.AppDataBase_Impl
import com.example.newschoco.data.model.everything.EverythingArticles
import com.example.newschoco.data.model.headline.Articles
import com.example.newschoco.data.pagination.HeadLinePag
import com.example.newschoco.data.pagination.PaginationWithoutCasheEverything
import com.example.newschoco.data.remote.NewsService
import java.util.concurrent.Flow

interface NewsRepository{
    @ExperimentalPagingApi
    fun getPagingResult():LiveData<PagingData<Articles>>
    fun getFeatureNewsHeadLine(): LiveData<List<Articles>>
    @ExperimentalPagingApi
    fun getPagingAllNews(query:String): kotlinx.coroutines.flow.Flow<PagingData<EverythingArticles>>

}

class NewsRepositoryImpl(private val network: NewsService,private val db : AppDataBase) : NewsRepository{



    @ExperimentalPagingApi
    override fun getPagingResult(): LiveData<PagingData<Articles>> {
       val pagingSourceFactory = {db.getContentDao()
           .getAll()}
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE,enablePlaceholders = false),
            remoteMediator = HeadLinePag(network,db),
            pagingSourceFactory = pagingSourceFactory
        ).liveData
    }

    override fun getFeatureNewsHeadLine(): LiveData<List<Articles>>  = db.getContentDao().getAllHeadLineNews()

    @ExperimentalPagingApi
   override fun getPagingAllNews(query: String): kotlinx.coroutines.flow.Flow<PagingData<EverythingArticles>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE_NO_CASHE),
            pagingSourceFactory = {PaginationWithoutCasheEverything(network, query)}
        ).flow
    }

    companion object{
        const val  PAGE_SIZE = 3
        const val  PAGE_SIZE_NO_CASHE = 20
    }

}