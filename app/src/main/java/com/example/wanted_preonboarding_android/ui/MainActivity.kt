package com.example.wanted_preonboarding_android.ui

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.wanted_preonboarding_android.R
import com.example.wanted_preonboarding_android.db.ArticleDatabase
import com.example.wanted_preonboarding_android.repository.NewsRepository

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: NewsViewModel
    lateinit var nav_view: BottomNavigationView
    lateinit var newNavHostFragment: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)
        nav_view = findViewById(R.id.nav_view)
        newNavHostFragment = findViewById(R.id.newsNavHostFragment)
        nav_view.setupWithNavController(newNavHostFragment.findNavController())
    }
}