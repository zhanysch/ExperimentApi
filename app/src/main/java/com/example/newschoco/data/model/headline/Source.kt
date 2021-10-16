package com.example.newschoco.data.model.headline
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Source (
	@PrimaryKey
	val id : String,
	val name : String
):Parcelable