package com.vipul.kmp.news

import androidx.compose.ui.Alignment
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import java.awt.Dimension

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "KMP-News-APP",
        state = WindowState(
            position = WindowPosition(Alignment.Center)
        )
    ) {
        window.minimumSize = Dimension(1280, 768)
        App()
    }
}