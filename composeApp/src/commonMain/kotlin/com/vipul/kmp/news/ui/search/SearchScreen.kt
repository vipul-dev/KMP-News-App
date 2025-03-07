package com.vipul.kmp.news.ui.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.vipul.kmp.news.di.koinViewModel
import com.vipul.kmp.news.theme.xSmallPadding
import com.vipul.kmp.news.ui.component.ArticleListScreen
import com.vipul.kmp.news.ui.component.EmptyContent
import com.vipul.kmp.news.ui.component.LoadingShimmerEffect
import com.vipul.kmp.news.ui.component.SearchBarScreen
import com.vipul.kmp.news.ui.navigation.SettingScreenRoute
import com.vipul.kmp.news.utils.navigationItemList
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.ic_browse
import kmp_news_app.composeapp.generated.resources.ic_network_error
import kmp_news_app.composeapp.generated.resources.no_news
import kmp_news_app.composeapp.generated.resources.setting
import kmp_news_app.composeapp.generated.resources.type_to_search
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavController, paddingValues: PaddingValues) {
    var searchQuery by rememberSaveable {
        mutableStateOf("")
    }
    val searchViewmodel = koinViewModel<SearchViewmodel>()
    val uiState by searchViewmodel.newsStateFlow.collectAsState()

    Column(
        verticalArrangement = Arrangement.spacedBy(xSmallPadding), modifier = Modifier.fillMaxSize().padding(paddingValues)
    ) {
        TopAppBar(title = {
            Text(
                text = stringResource(navigationItemList[1].title),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
        }, actions = {
            IconButton(onClick = {
                navController.navigate(SettingScreenRoute.Setting.route)
            }) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = stringResource(Res.string.setting)
                )
            }
        })
        SearchBarScreen(text = searchQuery, onValueChange = {
            searchQuery = it
        }, onSearch = { query ->
            if (query.trim().isNotEmpty()) {
                println(query)
                searchViewmodel.searchQueryNews(query)
            }
        })


        uiState.DisplayResult(onIdle = {
            EmptyContent(
                message = stringResource(Res.string.type_to_search),
                icon = Res.drawable.ic_browse,
                isOnRetryBtnVisible = false
            )
        }, onLoading = {
            LoadingShimmerEffect()
        }, onSuccess = {
            if (it.isEmpty()) {
                EmptyContent(
                    message = stringResource(Res.string.no_news),
                    icon = Res.drawable.ic_browse,

                    )
            } else {
                ArticleListScreen(it, navController)
            }
        }, onError = {
            EmptyContent(message = it, icon = Res.drawable.ic_network_error, onRetryClick = {
                if (searchQuery.trim().isNotEmpty()) {
                    searchViewmodel.searchQueryNews(searchQuery)
                }
            })
        })
    }
}