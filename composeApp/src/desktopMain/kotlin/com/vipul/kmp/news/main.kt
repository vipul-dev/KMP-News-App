package com.vipul.kmp.news

import androidx.compose.ui.Alignment
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import com.vipul.kmp.news.di.initKoin
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.logo
import org.jetbrains.compose.resources.painterResource
import java.awt.Dimension

fun main() = application {
    initKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "KMP-News-APP",
        state = WindowState(
            position = WindowPosition(Alignment.Center)
        ),
        icon = painterResource(Res.drawable.logo)
    ) {
        window.minimumSize = Dimension(640, 480)
        App()
    }
}