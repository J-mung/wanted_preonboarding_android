package com.example.wanted_preonboarding_android.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.wanted_preonboarding_android.models.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article): Long // returns the id's of the article

    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)
}