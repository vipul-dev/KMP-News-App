package com.vipul.kmp.news

import androidx.compose.ui.window.ComposeUIViewController
import com.vipul.kmp.news.di.initKoin

fun MainViewController() = ComposeUIViewController(configure = {
    initKoin()
}) { App() }