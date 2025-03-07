package com.vipul.kmp.news.ui.headline

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vipul.kmp.news.models.Article
import com.vipul.kmp.news.models.ErrorResponse
import com.vipul.kmp.news.models.NewsResponse
import com.vipul.kmp.news.repository.OnlineNewsRepository
import com.vipul.kmp.news.utils.Resource
import com.vipul.kmp.news.utils.categoryList
import io.ktor.client.call.body
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HeadlineViewmodel(
    private val newsRepository: OnlineNewsRepository
) : ViewModel() {


    private val _newsStateFlow = MutableStateFlow<Resource<List<Article>>>(Resource.Loading)
    val newsStateFlow: StateFlow<Resource<List<Article>>>
        get() = _newsStateFlow

    var category by mutableStateOf(categoryList[0])

    init {
        getHeadlines(category)
    }

     fun getHeadlines(category:String) {
        viewModelScope.launch(Dispatchers.IO) {
            _newsStateFlow.emit(Resource.Loading)
            delay(1500)
            try {
                val httpResponse = newsRepository.getNews(category = category)
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