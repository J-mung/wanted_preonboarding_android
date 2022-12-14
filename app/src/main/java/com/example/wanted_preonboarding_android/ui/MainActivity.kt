package com.example.wanted_preonboarding_android.ui

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.wanted_preonboarding_android.R
import com.example.wanted_preonboarding_android.db.ArticleDatabase
import com.example.wanted_preonboarding_android.repository.NewsRepository

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: NewsViewModel
    lateinit var nav_view: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)
        nav_view = findViewById(R.id.nav_view)
        val newNavHostFragment = supportFragmentManager.findFragmentById(R.id.newNavHostFragment) as NavHostFragment
        val navController = newNavHostFragment.navController
        nav_view = findViewById(R.id.nav_view)
        nav_view.setupWithNavController(navController)
    }
}