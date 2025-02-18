package com.vipul.kmp.news.ui.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vipul.kmp.news.models.Article
import com.vipul.kmp.news.repository.LocalNewsRepository
import com.vipul.kmp.news.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class BookmarkViewmodel(
    private val newsRepository: LocalNewsRepository
) : ViewModel() {


    private val _newsStateFlow = MutableStateFlow<Resource<List<Article>>>(Resource.Loading)
    val newsStateFlow: StateFlow<Resource<List<Article>>>
        get() = _newsStateFlow

    init {
        getHeadlines()
    }

    fun getHeadlines() {
        viewModelScope.launch(Dispatchers.IO) {
            _newsStateFlow.emit(Resource.Loading)
            delay(2500)
            try {
                newsRepository.getArticle().catch {
                    it.printStackTrace()
                    _newsStateFlow.emit(Resource.Error(it.message.toString()))
                }.collect { articleList ->
                    _newsStateFlow.emit(Resource.Success(articleList))
                }
            } catch (e: Exception) {
                _newsStateFlow.emit(Resource.Error(e.message.toString()))
                e.printStackTrace()
            }
        }
    }
}