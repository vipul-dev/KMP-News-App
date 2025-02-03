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
import com.vipul.kmp.news.theme.mediumPadding
import com.vipul.kmp.news.ui.component.ArticleListScreen
import com.vipul.kmp.news.ui.component.EmptyContent
import com.vipul.kmp.news.ui.component.LoadingShimmerEffect
import com.vipul.kmp.news.ui.component.SearchBarScreen

@Composable
fun SearchScreen() {
    var searchQuery by rememberSaveable {
        mutableStateOf("")
    }
    val searchViewmodel = viewModel {
        SearchViewmodel()
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
                EmptyContent("Type to search")
            },
            onLoading = {
                LoadingShimmerEffect()
            },
            onSuccess = {
                if (it.isEmpty()) {
                    EmptyContent("No News")
                } else {
                    ArticleListScreen(it)
                }
            },
            onError = {
                EmptyContent(it)
            }
        )
    }
}