package com.vipul.kmp.news.ui.headline

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.vipul.kmp.news.di.koinViewModel
import com.vipul.kmp.news.theme.xSmallPadding
import com.vipul.kmp.news.ui.component.ArticleListScreen
import com.vipul.kmp.news.ui.component.EmptyContent
import com.vipul.kmp.news.ui.component.LoadingShimmerEffect
import com.vipul.kmp.news.ui.navigation.SettingScreenRoute
import com.vipul.kmp.news.utils.navigationItemList
import com.vipul.kmp.news.utils.categoryList
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.ic_browse
import kmp_news_app.composeapp.generated.resources.ic_network_error
import kmp_news_app.composeapp.generated.resources.no_news
import kmp_news_app.composeapp.generated.resources.setting
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeadlineScreen(navController: NavController, paddingValues: PaddingValues) {
    val headlineViewmodel = koinViewModel<HeadlineViewmodel>()

    val uiState by headlineViewmodel.newsStateFlow.collectAsState()


    Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
        TopAppBar(title = {
            Text(
                text = stringResource(navigationItemList[0].title),
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
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = xSmallPadding),
            horizontalArrangement = Arrangement.spacedBy(
                xSmallPadding,
                Alignment.CenterHorizontally
            )
        ) {
            items(categoryList, key = {
                it
            }) { category ->
                FilterChip(
                    selected = headlineViewmodel.category == category,
                    onClick = {
                        headlineViewmodel.category = category
                        headlineViewmodel.getHeadlines(headlineViewmodel.category)
                    },
                    label = {
                        Text(category)
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainer
                    )

                )

            }
        }

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
                            headlineViewmodel.getHeadlines(headlineViewmodel.category)
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
                        headlineViewmodel.getHeadlines(headlineViewmodel.category)
                    }
                )
            }
        )
    }

}