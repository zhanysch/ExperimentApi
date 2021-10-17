package com.example.newschoco.data.model.everything

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EverythingSource (

	val id : String,
	val name : String
) : Parcelable