package com.example.newschoco.data.model.headline

import android.text.TextUtils
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object TypeConverter {

    @JvmStatic
    @TypeConverter
    fun headString(model: BaseHeadLineModel): String {
        return Gson().toJson(model)
    }

    @JvmStatic
    @TypeConverter
    fun headObject(text: String): BaseHeadLineModel? {
        if (TextUtils.isEmpty(text))
            return null
        return Gson().fromJson(text, BaseHeadLineModel::class.java)
    }



    @JvmStatic
    @TypeConverter
    fun photosToString(model: List<Articles>): String {
        return Gson().toJson(model)
    }

    @JvmStatic
    @TypeConverter
    fun photosToObject(text: String?): List<Articles>? {
        if (text == null) return mutableListOf()
        val typeToken = object : TypeToken<List<Articles>>() {}.type
        return Gson().fromJson(text, typeToken)
    }



    @JvmStatic
    @TypeConverter
    fun sourceString(model: Source): String {
        return Gson().toJson(model)
    }

    @JvmStatic
    @TypeConverter
    fun sourceObject(text: String): Source? {
        if (TextUtils.isEmpty(text))
            return null
        return Gson().fromJson(text, Source::class.java)
    }
}