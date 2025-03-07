package com.vipul.kmp.news.ui.navigation.graphs

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vipul.kmp.news.models.Article
import com.vipul.kmp.news.ui.articleDetail.ArticleDetailScreen
import com.vipul.kmp.news.ui.navigation.Graph
import com.vipul.kmp.news.ui.navigation.NewsScreenRoute
import com.vipul.kmp.news.ui.navigation.SettingScreenRoute
import com.vipul.kmp.news.ui.setting.SettingScreen
import com.vipul.kmp.news.ui.setting.SettingViewModel
import kotlinx.serialization.json.Json

@Composable
fun RootNavGraph(
    settingViewModel: SettingViewModel,
    rootNavController: NavHostController,
    innerPadding: PaddingValues
) {

    NavHost(
        navController = rootNavController,
        route = Graph.RootGraph,
        startDestination = Graph.MainScreenGraph
    ) {
        mainNavGraph(rootNavController, innerPadding)
        composable(route = NewsScreenRoute.NewsDetails.route) {
            rootNavController.previousBackStackEntry?.savedStateHandle?.get<String>("article")
                ?.let {
                    val article = Json.decodeFromString<Article>(it)
                    ArticleDetailScreen(rootNavController, article)
                }
        }

        composable(route = SettingScreenRoute.Setting.route) {
            SettingScreen(rootNavController, settingViewModel)
        }
    }
}