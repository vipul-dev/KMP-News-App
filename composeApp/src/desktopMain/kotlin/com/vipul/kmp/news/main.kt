package com.vipul.kmp.news

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "KMP-News-APP",
    ) {
        App()
    }
}