package com.example.newschoco.ui.bottomNavigation.features

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.newschoco.data.db.AppDataBase
import com.example.newschoco.data.model.headline.Articles
import com.example.newschoco.data.repository.NewsRepository

class FeatureViewModel(private val repository : NewsRepository, private val db : AppDataBase): ViewModel() {

    fun getSavedFeatureNewsHeadLine(): LiveData<List<Articles>>{
        return repository.getFeatureNewsHeadLine()
    }

    fun update(item : Articles){
        db.getContentDao().updateDetailArticles(item)
    }
}