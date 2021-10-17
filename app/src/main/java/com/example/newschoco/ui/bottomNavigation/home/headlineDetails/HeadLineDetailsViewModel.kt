package com.example.newschoco.ui.bottomNavigation.home.headlineDetails

import androidx.lifecycle.ViewModel
import com.example.newschoco.data.db.AppDataBase
import com.example.newschoco.data.model.headline.Articles
import com.example.newschoco.data.repository.NewsRepository

class HeadLineDetailsViewModel(private val repository: NewsRepository, private val db : AppDataBase): ViewModel() {

    fun update(item : Articles){
        db.getContentDao().updateDetailArticles(item)
    }
}