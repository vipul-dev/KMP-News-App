package com.vipul.kmp.news.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vipul.kmp.news.models.Article
import com.vipul.kmp.news.models.ErrorResponse
import com.vipul.kmp.news.models.NewsResponse
import com.vipul.kmp.news.repository.NewsRepository
import com.vipul.kmp.news.utils.Resource
import io.ktor.client.call.body
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewmodel(private val newsRepository: NewsRepository) : ViewModel() {
    private val _newsStateFlow = MutableStateFlow<Resource<List<Article>>>(Resource.Idle)
    val newsStateFlow: StateFlow<Resource<List<Article>>>
        get() = _newsStateFlow


    fun searchQueryNews(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _newsStateFlow.emit(Resource.Loading)
            try {
                val httpResponse = newsRepository.searchNews(query)
                if (httpResponse.status.value in 200..299) {
                    val body = httpResponse.body<NewsResponse>()
                    _newsStateFlow.emit(Resource.Success(body.articles))
                } else {
                    val body = httpResponse.body<ErrorResponse>()
                    _newsStateFlow.emit(Resource.Error(body.message))
                }
            } catch (e: Exception) {
                _newsStateFlow.emit(Resource.Error(e.message.toString()))
                e.printStackTrace()
            }
        }
    }
}