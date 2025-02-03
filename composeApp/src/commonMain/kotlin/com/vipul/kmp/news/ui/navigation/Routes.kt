package com.vipul.kmp.news.ui.navigation

object Graph{
    const val RootGraph = "rootScreenGraph"
    const val MainScreenGraph = "mainScreenGraph"
}

sealed class MainScreenRoute(var route:String){
    object Headline:MainScreenRoute("headline")
    object Search:MainScreenRoute("search")
    object Bookmark:MainScreenRoute("bookmark")
}

sealed class SettingScreenRoute(var route:String){
    object Setting:SettingScreenRoute("setting")
}