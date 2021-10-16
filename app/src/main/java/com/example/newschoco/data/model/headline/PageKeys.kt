package com.example.newschoco.data.model.headline

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PageKeys (
    @PrimaryKey
    val id : Int,
    val prevKey : Int?,
    val nextKey : Int?

)