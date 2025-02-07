package com.vipul.kmp.news.utils

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import com.vipul.kmp.news.ui.navigation.BottomNavigationItem
import com.vipul.kmp.news.ui.navigation.MainScreenRoute
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.bookmark
import kmp_news_app.composeapp.generated.resources.dark_mode
import kmp_news_app.composeapp.generated.resources.headlines
import kmp_news_app.composeapp.generated.resources.ic_bookmark_outline
import kmp_news_app.composeapp.generated.resources.ic_headline
import kmp_news_app.composeapp.generated.resources.ic_search
import kmp_news_app.composeapp.generated.resources.light_mode
import kmp_news_app.composeapp.generated.resources.search
import kmp_news_app.composeapp.generated.resources.system_default
import org.jetbrains.compose.resources.StringResource
import kotlin.random.Random


const val dataStoreFileName = "setting.preferences_pb"
const val BASE_URl = "https://newsapi.org/v2/"

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


enum class Type {
    Mobile, Desktop
}

enum class Theme(val title: StringResource) {
    SYSTEM_DEFAULT(Res.string.system_default),
    LIGHT_MODE(Res.string.light_mode),
    DARK_MODE(Res.string.dark_mode)
}


val FadeIN = fadeIn(animationSpec = tween(220, delayMillis = 90)) +
        scaleIn(
            initialScale = 0.92f,
            animationSpec = tween(220, delayMillis = 90)
        )

val FadeOUT = fadeOut(animationSpec = tween(90))