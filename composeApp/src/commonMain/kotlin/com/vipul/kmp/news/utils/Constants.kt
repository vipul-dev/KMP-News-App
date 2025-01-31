package com.vipul.kmp.news.utils

import com.vipul.kmp.news.ui.navigation.BottomNavigationItem
import com.vipul.kmp.news.ui.navigation.MainScreenRoute
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.*


val bottomNavigationList = listOf(
    BottomNavigationItem(
        icon = Res.drawable.ic_headline,
        title = Res.string.headlines,
        route = MainScreenRoute.Headline.route
    ),
    BottomNavigationItem(
        icon = Res.drawable.ic_search,
        title = Res.string.search,
        route = MainScreenRoute.Search.route
    ),
    BottomNavigationItem(
        icon = Res.drawable.ic_bookmark_outline,
        title = Res.string.bookmark,
        route = MainScreenRoute.Bookmark.route
    ),
)