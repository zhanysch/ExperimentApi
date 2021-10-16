package com.example.newschoco.data.db

import android.content.Context
import androidx.room.*
import com.example.newschoco.data.model.headline.*
import com.example.newschoco.data.model.headline.TypeConverter

@Database(entities = [BaseHeadLineModel::class, Articles::class,Source::class,
    PageKeys::class],version = 2,exportSchema = true)
@TypeConverters(value = [TypeConverter::class])
abstract class AppDataBase: RoomDatabase() {
    abstract fun getContentDao(): NewsDao
    abstract fun getPagingKeysDao(): PageKeysDao

    companion object{
        fun getInstanceDB(context:Context): AppDataBase{
            return Room.databaseBuilder(context, AppDataBase::class.java,"myDB")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}
