package com.vipul.kmp.news.ui.bookmark

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun BookmarkScreen() {
    Box(modifier = Modifier.fillMaxSize()){
        Text(text = "Bookmark", fontSize = 32.sp, modifier = Modifier.align(Alignment.Center))
    }
}