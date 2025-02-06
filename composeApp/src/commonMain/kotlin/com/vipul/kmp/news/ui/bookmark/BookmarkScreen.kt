package com.vipul.kmp.news.ui.bookmark

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.vipul.kmp.news.ui.component.ArticleListScreen
import com.vipul.kmp.news.ui.component.EmptyContent
import com.vipul.kmp.news.ui.component.LoadingShimmerEffect

@Composable
fun BookmarkScreen(navController: NavController) {
    val bookmarkViewmodel = viewModel {
        BookmarkViewmodel()
    }

    val uiState by bookmarkViewmodel.newsStateFlow.collectAsState()
    uiState.DisplayResult(
        onIdle = {

        },
        onLoading = {
            LoadingShimmerEffect()
        },
        onSuccess = {
            if (it.isEmpty()) {
                EmptyContent("No Data")
            } else {
                ArticleListScreen(it,navController)
            }
        },
        onError = {
            EmptyContent(it)
        }
    )
}