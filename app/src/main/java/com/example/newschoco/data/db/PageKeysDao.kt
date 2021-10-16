package com.example.newschoco.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newschoco.data.model.headline.PageKeys

@Dao
interface PageKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(data: kotlin.collections.List<PageKeys>)

    @Query("SELECT * FROM pagekeys WHERE id = :id")
    suspend fun getKeyId(id: Int?): PageKeys?

    @Query("DELETE FROM pagekeys")
    suspend fun deleteAll()
}