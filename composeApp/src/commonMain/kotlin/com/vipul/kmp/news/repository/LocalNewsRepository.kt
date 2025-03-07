package com.vipul.kmp.news.repository

import com.vipul.kmp.news.database.NewsDao
import com.vipul.kmp.news.models.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.flowOn

class LocalNewsRepository(
    private val newsDao: NewsDao
) {
    suspend fun upsertArticle(article: Article) {
        newsDao.insertOrUpdateNews(article)
    }

    suspend fun deleteAllArticle() {
        newsDao.deleteAllArticle()
    }

    suspend fun deleteArticle(article: Article) {
        newsDao.deleteArticle(article)
    }

    fun getArticles() = newsDao.getArticles().flowOn(Dispatchers.IO)

    suspend fun getArticle(articleId: String): Article? {
        return newsDao.getArticle(articleId)
    }
}