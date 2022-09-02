package com.example.wanted_preonboarding_android.models

data class NewsResponse(
    val article: MutableList<Article>,
    val status: String,
    val totalResults: Int
)
