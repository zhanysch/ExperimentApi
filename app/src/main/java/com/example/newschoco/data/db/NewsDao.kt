package com.example.newschoco.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newschoco.data.model.headline.Articles

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: List<Articles>)

    @Query("SELECT * FROM Articles")
    fun getAll(): PagingSource<Int, Articles>

    @Query("DELETE FROM Articles")
    suspend fun deleteAll()
}