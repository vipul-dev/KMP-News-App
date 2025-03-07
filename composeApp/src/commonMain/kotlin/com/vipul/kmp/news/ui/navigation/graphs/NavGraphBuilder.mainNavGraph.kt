package com.vipul.kmp.news.ui.navigation.graphs

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.vipul.kmp.news.ui.bookmark.BookmarkScreen
import com.vipul.kmp.news.ui.headline.HeadlineScreen
import com.vipul.kmp.news.ui.navigation.Graph
import com.vipul.kmp.news.ui.navigation.MainScreenRoute
import com.vipul.kmp.news.ui.search.SearchScreen

fun NavGraphBuilder.mainNavGraph(
    rootNavController: NavHostController,
    paddingValues: PaddingValues
) {
    navigation(
        route = Graph.MainScreenGraph,
        startDestination = MainScreenRoute.Headline.route
    ) {
        composable(route = MainScreenRoute.Headline.route) {
            HeadlineScreen(rootNavController,paddingValues)
        }
        composable(route = MainScreenRoute.Search.route) {
            SearchScreen(rootNavController,paddingValues)
        }
        composable(route = MainScreenRoute.Bookmark.route) {
            BookmarkScreen(rootNavController,paddingValues)
        }
    }
}