package com.vipul.kmp.news.ui.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vipul.kmp.news.ui.MainScreen
import com.vipul.kmp.news.ui.articleDetail.ArticleDetailScreen
import com.vipul.kmp.news.ui.navigation.Graph
import com.vipul.kmp.news.ui.navigation.NewsScreenRoute
import com.vipul.kmp.news.ui.navigation.SettingScreenRoute
import com.vipul.kmp.news.ui.setting.SettingScreen
import com.vipul.kmp.news.ui.setting.SettingViewModel
import com.vipul.kmp.news.utils.articles

@Composable
fun RootNavGraph(settingViewModel: SettingViewModel) {
    val rootNavController = rememberNavController()
    NavHost(
        navController = rootNavController,
        route = Graph.RootGraph,
        startDestination = Graph.MainScreenGraph
    ) {
        composable(route = Graph.MainScreenGraph) {
            MainScreen(rootNavController)
        }

        composable(route = NewsScreenRoute.NewsDetails.route) {
            ArticleDetailScreen(rootNavController, articles[0])
        }

        composable(route = SettingScreenRoute.Setting.route) {
            SettingScreen(rootNavController, settingViewModel)
        }
    }
}