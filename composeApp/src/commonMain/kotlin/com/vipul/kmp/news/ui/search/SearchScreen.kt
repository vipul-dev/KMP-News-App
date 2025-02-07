package com.vipul.kmp.news.ui.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.vipul.kmp.news.repository.NewsRepository
import com.vipul.kmp.news.theme.mediumPadding
import com.vipul.kmp.news.ui.component.ArticleListScreen
import com.vipul.kmp.news.ui.component.EmptyContent
import com.vipul.kmp.news.ui.component.LoadingShimmerEffect
import com.vipul.kmp.news.ui.component.SearchBarScreen
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.ic_browse
import kmp_news_app.composeapp.generated.resources.ic_network_error
import kmp_news_app.composeapp.generated.resources.no_news
import kmp_news_app.composeapp.generated.resources.type_to_search
import org.jetbrains.compose.resources.stringResource

@Composable
fun SearchScreen(navController: NavController) {
    var searchQuery by rememberSaveable {
        mutableStateOf("")
    }
    val searchViewmodel = viewModel {
        SearchViewmodel(NewsRepository())
    }
    val uiState by searchViewmodel.newsStateFlow.collectAsState()

    Column(
        verticalArrangement = Arrangement.spacedBy(mediumPadding),
        modifier = Modifier.fillMaxSize()
    ) {
        SearchBarScreen(
            text = searchQuery,
            onValueChange = {
                searchQuery = it
            },
            onSearch = { query ->
                if (query.trim().isNotEmpty()) {
                    println(query)
                    searchViewmodel.searchQueryNews(query)
                }
            }
        )


        uiState.DisplayResult(
            onIdle = {
                EmptyContent(
                    message = stringResource(Res.string.type_to_search),
                    icon = Res.drawable.ic_browse,
                    isOnRetryBtnVisible = false
                )
            },
            onLoading = {
                LoadingShimmerEffect()
            },
            onSuccess = {
                if (it.isEmpty()) {
                    EmptyContent(
                        message = stringResource(Res.string.no_news),
                        icon = Res.drawable.ic_browse,

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
                        if (searchQuery.trim().isNotEmpty()) {
                            searchViewmodel.searchQueryNews(searchQuery)
                        }
                    }
                )
            }
        )
    }
}