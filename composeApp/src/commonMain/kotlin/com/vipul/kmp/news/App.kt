package com.vipul.kmp.news

import androidx.compose.runtime.Composable
import com.vipul.kmp.news.theme.KmpNewsAppTheme
import com.vipul.kmp.news.ui.MainScreen
import com.vipul.kmp.news.ui.navigation.graphs.RootNavGraph
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    KmpNewsAppTheme(true) {
        RootNavGraph()
    }
}