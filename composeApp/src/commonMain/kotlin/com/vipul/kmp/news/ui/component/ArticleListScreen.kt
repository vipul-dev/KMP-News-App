package com.vipul.kmp.news.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.vipul.kmp.news.models.Article
import com.vipul.kmp.news.theme.cardMinSize
import com.vipul.kmp.news.theme.mediumPadding
import com.vipul.kmp.news.ui.navigation.NewsScreenRoute
import com.vipul.kmp.news.utils.randomUUIDStr
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun ArticleListScreen(articleList: List<Article>, navController: NavController) {

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(cardMinSize),
        verticalItemSpacing = mediumPadding,
        horizontalArrangement = Arrangement.spacedBy(mediumPadding),
        contentPadding = PaddingValues(mediumPadding),
        modifier = Modifier.fillMaxHeight()
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