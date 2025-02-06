package com.vipul.kmp.news

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vipul.kmp.news.theme.KmpNewsAppTheme
import com.vipul.kmp.news.ui.navigation.graphs.RootNavGraph
import com.vipul.kmp.news.ui.setting.SettingViewModel
import com.vipul.kmp.news.utils.AppPreferences
import com.vipul.kmp.news.utils.dataStorePreference
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val appPreferences = remember {
        AppPreferences(dataStorePreference())
    }

    val settingViewModel = viewModel {
        SettingViewModel(appPreferences)
    }

    val currentTheme by settingViewModel.currentTheme.collectAsState()

    KmpNewsAppTheme(currentTheme) {
        RootNavGraph(settingViewModel)
    }
}