package com.example.wanted_preonboarding_android.repository

import com.example.wanted_preonboarding_android.api.RetrofitInstance
import com.example.wanted_preonboarding_android.db.ArticleDatabase
import com.example.wanted_preonboarding_android.models.Article

class NewsRepository(
    val db: ArticleDatabase
) {
    suspend fun getTopHeadlineNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getTopHeadlineNews(countryCode, pageNumber)

    suspend fun categoryNews(countryCode: String, categoryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getCategoryNews(countryCode, categoryCode, pageNumber)

    suspend fun upsert(article: Article) = db.getArticleDao().upsert(article)

    fun getSavedNews() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)
}