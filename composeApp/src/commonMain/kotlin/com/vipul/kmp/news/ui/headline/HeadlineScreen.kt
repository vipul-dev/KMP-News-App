package com.vipul.kmp.news.ui.headline

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun HeadlineScreen() {
    Box(modifier = Modifier.fillMaxSize()){
        Text(text = "Headline", fontSize = 32.sp, modifier = Modifier.align(Alignment.Center))
    }
}