package com.vipul.kmp.news.ui.articleDetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vipul.kmp.news.models.Article
import com.vipul.kmp.news.repository.LocalNewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class ArticleDetailsViewModel(
    private val localNewsRepository: LocalNewsRepository
) : ViewModel() {

    var isBookmarked by mutableStateOf(false)

    fun isArticleBookmark(currentArticle: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            isBookmarked = localNewsRepository.getArticle(currentArticle.publishedAt) != null
        }
    }

    fun bookmarkArticle(currentArticle: Article) {
        viewModelScope.launch(Dispatchers.IO) {

            if (!isBookmarked) {
                localNewsRepository.upsertArticle(currentArticle)
            } else {
                localNewsRepository.deleteArticle(currentArticle)
            }
            isBookmarked = !isBookmarked

        }
    }


}