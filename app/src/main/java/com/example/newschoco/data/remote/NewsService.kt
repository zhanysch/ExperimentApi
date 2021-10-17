package com.example.newschoco.data.remote

import com.example.newschoco.data.model.everything.BaseEverythingModel
import com.example.newschoco.data.model.everything.EverythingArticles
import com.example.newschoco.data.model.headline.Articles
import com.example.newschoco.data.model.headline.BaseHeadLineModel
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("v2/top-headlines")
    suspend fun getHeadLines(
        @Query("country") country : String,
        @Query("category") category : String,
        @Query("page") page :Int,
        @Query("pageSize") pageSize:Int
    ):BaseHeadLineModel

    @GET("v2/everything")
    suspend fun getAllNews(
        @Query("q") query : String,
        @Query("sortBy") sortBy : String,
        @Query("language")language:String,
        @Query("page") page: Int,
        @Query("pageSize")pageSize: Int
    ): BaseEverythingModel<EverythingArticles>
}