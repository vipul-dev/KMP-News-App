package com.vipul.kmp.news.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vipul.kmp.news.models.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateNews(article: Article)

    @Query("Select * From articleTable")
    fun getArticle(): Flow<List<Article>>

}