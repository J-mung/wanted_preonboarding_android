package com.example.wanted_preonboarding_android.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wanted_preonboarding_android.models.NewsResponse
import com.example.wanted_preonboarding_android.repository.NewsRepository
import com.example.wanted_preonboarding_android.utils.Resource
import kotlinx.coroutines.launch

class NewsViewModel(
    val newsRepository: NewsRepository
) : ViewModel() {

    val topHeadlinesNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    val topHeadlinesNewsPage = 1
    val topHeadlinesNewsResponse: NewsResponse? = null

    val categoryNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    val categoryNewsPage = 1
    val categoryNewsResponse: NewsResponse? = null

    init {
        getTopHeadlineNews("us")
    }

    fun getTopHeadlineNews(countryCode: String) = viewModelScope.launch {
        topHeadlinesNews.postValue(Resource.Loading())

    }
}