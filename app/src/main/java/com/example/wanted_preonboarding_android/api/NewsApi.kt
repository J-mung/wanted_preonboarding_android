package com.example.wanted_preonboarding_android.api

import com.example.wanted_preonboarding_android.models.NewsResponse
import com.example.wanted_preonboarding_android.utils.Contants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country")
        countryCode: String = "us",
        @Query("category")
        categoryCode: String,
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResponse>
}