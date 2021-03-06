package com.example.newschoco

import android.app.Application
import com.example.newschoco.di.appsModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NewsApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin { androidContext(this@NewsApp)
        modules(appsModules)}
    }
}