package com.example.newschoco.data.model.everything

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EverythingArticles (
	val source : EverythingSource,
	val author : String,
	val title : String,
	val description : String,
	val url : String,
	val urlToImage : String,
	val publishedAt : String,
	val content : String
):Parcelable