package com.example.newschoco.data.model.headline

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Articles (
	@PrimaryKey
	val id:Int,
	val source : Source?,
	val author : String?,
	val title : String,
	val description : String?,
	val url : String?,
	val urlToImage : String?,
	val publishedAt : String?,
	val content : String?,
	var isChecked: Boolean = false,
): Parcelable