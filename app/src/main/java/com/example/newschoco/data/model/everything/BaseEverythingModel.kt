package com.example.newschoco.data.model.everything


data class BaseEverythingModel<T> (

	val status : String,
	val totalResults : Int,
	val articles : List<T>
)