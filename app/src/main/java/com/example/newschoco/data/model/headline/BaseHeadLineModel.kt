package com.example.newschoco.data.model.headline

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BaseHeadLineModel (
	@PrimaryKey(autoGenerate = true)
	val id : Int,
	val articles : List<Articles>
)