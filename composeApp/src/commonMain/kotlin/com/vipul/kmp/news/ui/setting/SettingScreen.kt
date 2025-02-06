package com.vipul.kmp.news.ui.setting

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.vipul.kmp.news.ui.component.DeleteBookmarkDialog
import com.vipul.kmp.news.ui.component.SettingItem
import com.vipul.kmp.news.ui.component.ThemeSelectionDialog
import com.vipul.kmp.news.utils.Theme
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.delete_bookmark
import kmp_news_app.composeapp.generated.resources.ic_delete
import kmp_news_app.composeapp.generated.resources.ic_light_mode
import kmp_news_app.composeapp.generated.resources.setting
import kmp_news_app.composeapp.generated.resources.theme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(rootNavController: NavHostController, settingViewModel: SettingViewModel) {

    var showDeleteBookmarkDialog by remember {
        mutableStateOf(false)
    }
    var showThemeSelectionDialog by remember {
        mutableStateOf(false)
    }

    val currentTheme by settingViewModel.currentTheme.collectAsState()

    when {
        showDeleteBookmarkDialog -> {
            DeleteBookmarkDialog(
                onDismissRequest = {
                    showDeleteBookmarkDialog = false
                },
                onDeleteBookmark = {
                    showDeleteBookmarkDialog = false
                }
            )
        }

        showThemeSelectionDialog -> {
            ThemeSelectionDialog(
                currentTheme = currentTheme ?: Theme.DARK_MODE.name,
                onThemeChange = {
                    settingViewModel.changeThemeMode(it.name)
                    showThemeSelectionDialog = false
                },
                onDismissRequest = {
                    showThemeSelectionDialog = false
                }
            )
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(Res.string.setting),
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        rootNavController.navigateUp()
                    }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(Res.string.setting)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            item {
                SettingItem(
                    onClick = {
                        showThemeSelectionDialog = true
                    },
                    painter = painterResource(Res.drawable.ic_light_mode),
                    itemName = stringResource(Res.string.theme)
                )
            }
            item {
                SettingItem(
                    onClick = {
                        showDeleteBookmarkDialog = true
                    },
                    painter = painterResource(Res.drawable.ic_delete),
                    itemName = stringResource(Res.string.delete_bookmark),
                    itemColor = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}