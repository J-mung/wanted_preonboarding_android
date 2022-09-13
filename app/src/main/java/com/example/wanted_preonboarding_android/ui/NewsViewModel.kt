package com.example.wanted_preonboarding_android.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wanted_preonboarding_android.models.Article
import com.example.wanted_preonboarding_android.models.NewsResponse
import com.example.wanted_preonboarding_android.repository.NewsRepository
import com.example.wanted_preonboarding_android.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(
    val newsRepository: NewsRepository
) : ViewModel() {

    val topHeadlinesNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var topHeadlinesNewsPage = 1
    var topHeadlinesNewsResponse: NewsResponse? = null

    val categoryNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var categoryNewsPage = 1
    var categoryNewsResponse: NewsResponse? = null

    init {
        getTopHeadlineNews("us")
    }

    fun getTopHeadlineNews(countryCode: String) = viewModelScope.launch {
        topHeadlinesNews.postValue(Resource.Loading())
        val response = newsRepository.getTopHeadlineNews(countryCode, topHeadlinesNewsPage)
        topHeadlinesNews.postValue(handleTopHeadlinesNewsResponse(response))
    }

    private fun handleTopHeadlinesNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                topHeadlinesNewsPage++
                if (topHeadlinesNewsResponse == null) {
                    topHeadlinesNewsResponse = resultResponse
                } else {
                    val olArticles = topHeadlinesNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    olArticles?.addAll(newArticles)
                }
                return Resource.Success(topHeadlinesNewsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun saveArticle(article: Article) = viewModelScope.launch {
        newsRepository.insert(article)
    }

    fun getSavedNews() = newsRepository.getSavedNews()

    fun deleteArticle(article: Article) = viewModelScope.launch {
        newsRepository.deleteArticle(article)
    }
}