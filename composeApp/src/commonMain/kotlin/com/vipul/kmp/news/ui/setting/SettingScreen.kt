package com.vipul.kmp.news.ui.setting

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun SettingScreen(rootNavController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Button(onClick = {
            rootNavController.navigateUp()
        }) {
            Text(text = "Back")
        }
        Text(text = "Setting", fontSize = 32.sp, modifier = Modifier.align(Alignment.Center))
    }
}