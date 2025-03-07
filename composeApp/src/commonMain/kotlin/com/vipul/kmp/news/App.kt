package com.vipul.kmp.news

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.vipul.kmp.news.di.koinViewModel
import com.vipul.kmp.news.theme.KmpNewsAppTheme
import com.vipul.kmp.news.ui.MainScreen
import com.vipul.kmp.news.ui.navigation.graphs.RootNavGraph
import com.vipul.kmp.news.ui.setting.SettingViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val settingViewModel = koinViewModel<SettingViewModel>()
    val currentTheme by settingViewModel.currentTheme.collectAsState()
    KmpNewsAppTheme(currentTheme) {
        MainScreen(settingViewModel)
    }
}