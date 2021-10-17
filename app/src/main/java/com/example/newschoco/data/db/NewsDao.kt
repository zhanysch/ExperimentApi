package com.example.newschoco.data.db

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.*
import com.example.newschoco.data.model.headline.Articles

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: List<Articles>)

    @Query("SELECT * FROM Articles")
    fun getAll(): PagingSource<Int, Articles>

    @Update
    fun updateDetailArticles(item : Articles)

    @Query("DELETE FROM Articles")
    suspend fun deleteAll()

    @Query("SELECT ALL * FROM ARTICLES WHERE isChecked")
    fun getAllHeadLineNews(): LiveData<List<Articles>>
}