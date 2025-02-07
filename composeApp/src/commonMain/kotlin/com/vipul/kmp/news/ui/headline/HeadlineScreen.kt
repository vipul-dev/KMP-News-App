package com.vipul.kmp.news.ui.headline

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.vipul.kmp.news.repository.NewsRepository
import com.vipul.kmp.news.ui.component.ArticleListScreen
import com.vipul.kmp.news.ui.component.EmptyContent
import com.vipul.kmp.news.ui.component.LoadingShimmerEffect
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.ic_browse
import kmp_news_app.composeapp.generated.resources.ic_network_error
import kmp_news_app.composeapp.generated.resources.no_news
import org.jetbrains.compose.resources.stringResource

@Composable
fun HeadlineScreen(navController: NavController) {
    val headlineViewmodel = viewModel {
        HeadlineViewmodel(NewsRepository())
    }

    val uiState by headlineViewmodel.newsStateFlow.collectAsState()
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
                    onRetryClick = {
                        headlineViewmodel.getHeadlines()
                    }
                )
            } else {
                ArticleListScreen(it, navController)
            }
        },
        onError = {
            EmptyContent(
                message = it,
                icon = Res.drawable.ic_network_error,
                onRetryClick = {
                    headlineViewmodel.getHeadlines()
                }
            )
        }
    )

}