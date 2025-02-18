package com.vipul.kmp.news.ui.bookmark

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.vipul.kmp.news.repository.LocalNewsRepository
import com.vipul.kmp.news.ui.component.ArticleListScreen
import com.vipul.kmp.news.ui.component.EmptyContent
import com.vipul.kmp.news.ui.component.LoadingShimmerEffect
import com.vipul.kmp.news.utils.getDatabaseBuilder
import com.vipul.kmp.news.utils.getRoomDatabase
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.ic_browse
import kmp_news_app.composeapp.generated.resources.no_news
import org.jetbrains.compose.resources.stringResource

@Composable
fun BookmarkScreen(navController: NavController) {
    val bookmarkViewmodel = viewModel {
        BookmarkViewmodel(LocalNewsRepository(getRoomDatabase(getDatabaseBuilder()).newsDao()))
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
                EmptyContent(
                    message = stringResource(Res.string.no_news),
                    icon = Res.drawable.ic_browse,
                    isOnRetryBtnVisible = false
                )
            } else {
                ArticleListScreen(it,navController)
            }
        },
        onError = {
            EmptyContent(
                message = it,
                icon = Res.drawable.ic_browse,
                onRetryClick = {
                    bookmarkViewmodel.getHeadlines()
                }
            )
        }
    )
}