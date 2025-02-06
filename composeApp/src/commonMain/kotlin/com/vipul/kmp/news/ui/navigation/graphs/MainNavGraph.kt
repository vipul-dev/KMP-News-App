package com.vipul.kmp.news.ui.navigation.graphs

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vipul.kmp.news.ui.bookmark.BookmarkScreen
import com.vipul.kmp.news.ui.headline.HeadlineScreen
import com.vipul.kmp.news.ui.navigation.Graph
import com.vipul.kmp.news.ui.navigation.MainScreenRoute
import com.vipul.kmp.news.ui.search.SearchScreen

@Composable
fun MainNavGraph(
    rootNavController: NavHostController,
    homeNavHostController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        modifier = Modifier.fillMaxWidth().padding(paddingValues),
        navController = homeNavHostController,
        route = Graph.MainScreenGraph,
        startDestination = MainScreenRoute.Headline.route
    ) {
        composable(route = MainScreenRoute.Headline.route) {
            HeadlineScreen(rootNavController)
        }
        composable(route = MainScreenRoute.Search.route) {
            SearchScreen(rootNavController)
        }
        composable(route = MainScreenRoute.Bookmark.route) {
            BookmarkScreen(rootNavController)
        }
    }
}