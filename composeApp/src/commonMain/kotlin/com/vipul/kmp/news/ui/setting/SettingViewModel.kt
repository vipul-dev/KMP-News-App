package com.vipul.kmp.news.ui.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vipul.kmp.news.repository.LocalNewsRepository
import com.vipul.kmp.news.utils.AppPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class SettingViewModel(
    private val appPreferences: AppPreferences,
    private val localNewsRepository: LocalNewsRepository
) : ViewModel() {
    private val _currentTheme: MutableStateFlow<String?> = MutableStateFlow(null)
    val currentTheme = _currentTheme.asStateFlow()


    init {
        currentThemeGet()
    }

    fun deleteAllBookmark() {
        viewModelScope.launch(Dispatchers.IO) {
            localNewsRepository.deleteAllArticle()
        }
    }

    private fun currentThemeGet() = runBlocking {
        _currentTheme.update {
            appPreferences.getTheme()
        }
    }

    fun changeThemeMode(value: String) {
        viewModelScope.launch(Dispatchers.IO) {
            appPreferences.changeThemeMode(value)
            _currentTheme.update {
                value
            }
        }
    }
}