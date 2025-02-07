package com.vipul.kmp.news.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.vipul.kmp.news.models.Article
import com.vipul.kmp.news.theme.xLargePadding
import com.vipul.kmp.news.ui.navigation.NewsScreenRoute
import com.vipul.kmp.news.utils.Type
import com.vipul.kmp.news.utils.getType
import com.vipul.kmp.news.utils.randomUUIDStr
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun ArticleListScreen(articleList: List<Article>, navController: NavController) {
    val isDesktop = remember {
        getType() == Type.Desktop
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(if (isDesktop) 3 else 1),
        verticalArrangement = Arrangement.spacedBy(xLargePadding),
        horizontalArrangement = Arrangement.spacedBy(xLargePadding),
        contentPadding = PaddingValues(xLargePadding)
    ) {
        items(articleList, key = {
            it.publishedAt + randomUUIDStr()
        }) { article ->
            ArticleItem(article = article, onClick = {
                val articleStr = Json.encodeToString(article)
                navController.currentBackStackEntry?.savedStateHandle?.apply {
                    set("article", articleStr)
                }
                navController.navigate(NewsScreenRoute.NewsDetails.route)
            })
        }
    }
}