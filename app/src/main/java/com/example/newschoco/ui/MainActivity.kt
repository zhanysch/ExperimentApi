package com.example.newschoco.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newschoco.R
import com.example.newschoco.databinding.ActivityMainBinding
import com.example.newschoco.utils.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBotomMenu()
    }



    private fun setupBotomMenu() {
        val navView = findViewById<BottomNavigationView>(R.id.bottomNav)

        val navId = listOf(
            R.navigation.home,
            R.navigation.feature,

        )

        navView.setupWithNavController(
            navGraphIds = navId,
            fragmentManager = supportFragmentManager,
            containerId = R.id.navView,
            intent = intent
        )
    }
}