package com.example.newschoco.di

import com.example.newschoco.data.db.AppDataBase
import com.example.newschoco.data.remote.RetrofitBuilder
import com.example.newschoco.data.repository.NewsRepository
import com.example.newschoco.data.repository.NewsRepositoryImpl
import com.example.newschoco.ui.bottomNavigation.features.FeatureViewModel
import com.example.newschoco.ui.bottomNavigation.home.all.AllNewsViewModel
import com.example.newschoco.ui.bottomNavigation.home.headlineDetails.HeadLineDetailsViewModel
import com.example.newschoco.ui.bottomNavigation.home.headlines.HeadLineVieModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule: Module = module {

    viewModel { HeadLineVieModel(get(),get())}
    viewModel { HeadLineDetailsViewModel(get(),get()) }
    viewModel { FeatureViewModel(get(),get()) }
    viewModel { AllNewsViewModel(get()) }
}
val repositoryModule : Module = module {
   single<NewsRepository>{NewsRepositoryImpl(get(),get())}
}

val dbModule : Module = module {
    single { AppDataBase.getInstanceDB(androidApplication()) }
}

val apiModule :Module = module {
single { RetrofitBuilder.buildRetrofit() }
}

val appsModules =
    listOf(viewModelModule, apiModule, dbModule, repositoryModule)