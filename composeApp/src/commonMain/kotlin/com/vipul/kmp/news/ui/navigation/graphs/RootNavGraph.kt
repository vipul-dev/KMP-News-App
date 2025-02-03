package com.vipul.kmp.news.ui.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vipul.kmp.news.ui.MainScreen
import com.vipul.kmp.news.ui.navigation.Graph
import com.vipul.kmp.news.ui.navigation.SettingScreenRoute
import com.vipul.kmp.news.ui.setting.SettingScreen

@Composable
fun RootNavGraph() {
    val rootNavController = rememberNavController()
    NavHost(
        navController = rootNavController,
        route = Graph.RootGraph,
        startDestination = Graph.MainScreenGraph
    ){
        composable(route= Graph.MainScreenGraph){
            MainScreen(rootNavController)
        }

        composable(route=SettingScreenRoute.Setting.route){
            SettingScreen(rootNavController)
        }
    }
}