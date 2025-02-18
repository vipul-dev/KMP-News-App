package com.vipul.kmp.news.ui.articleDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vipul.kmp.news.models.Article
import com.vipul.kmp.news.repository.LocalNewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class ArticleDetailsViewModel(
    private val localNewsRepository: LocalNewsRepository
):ViewModel() {

    fun bookmarkArticle(currentArticle: Article){
        viewModelScope.launch(Dispatchers.IO) {
            localNewsRepository.upsertArticle(currentArticle)
        }
    }

}