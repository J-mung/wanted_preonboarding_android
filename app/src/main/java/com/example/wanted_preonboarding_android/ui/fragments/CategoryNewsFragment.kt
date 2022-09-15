package com.example.wanted_preonboarding_android.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.wanted_preonboarding_android.R
import com.example.wanted_preonboarding_android.adapter.NewsAdapter
import com.example.wanted_preonboarding_android.ui.NewsViewModel

class CategoryNewsFragment : Fragment(R.layout.fragment_category_news){

    private val viewModel : NewsViewModel by activityViewModels()
    lateinit var newsAdapter : NewsAdapter

    val TAG = "CategoryNewsFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}