package com.vipul.kmp.news.ui.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vipul.kmp.news.models.Article
import com.vipul.kmp.news.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BookmarkViewmodel : ViewModel() {


    private val _newsStateFlow = MutableStateFlow<Resource<List<Article>>>(Resource.Idle)
    val newsStateFlow: StateFlow<Resource<List<Article>>>
        get() = _newsStateFlow

    init {
        getHeadlines()
    }

    private fun getHeadlines() {
        viewModelScope.launch(Dispatchers.IO) {
            _newsStateFlow.emit(Resource.Loading)
            delay(2500)
            try {
//                val articleList = articles
//                _newsStateFlow.emit(Resource.Success(articleList))
            } catch (e: Exception) {
                _newsStateFlow.emit(Resource.Error(e.message.toString()))
                e.printStackTrace()
            }
        }
    }
}