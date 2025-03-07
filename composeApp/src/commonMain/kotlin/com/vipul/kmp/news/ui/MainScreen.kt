package com.vipul.kmp.news.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.vipul.kmp.news.ui.navigation.NavigationSideBAr
import com.vipul.kmp.news.ui.navigation.NewsBottomNavigationBar
import com.vipul.kmp.news.ui.navigation.graphs.RootNavGraph
import com.vipul.kmp.news.ui.setting.SettingViewModel
import com.vipul.kmp.news.utils.navigationItemList

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun MainScreen(
    settingViewModel: SettingViewModel
) {

    val windowSizeClass = calculateWindowSizeClass()
    val isMediumExpendedWSC by remember(windowSizeClass) {
        derivedStateOf {
            windowSizeClass.widthSizeClass != WindowWidthSizeClass.Compact
        }
    }
    val rootNavController = rememberNavController()
    val navBackStackEntry by rootNavController.currentBackStackEntryAsState()


    val currentRoute by remember(navBackStackEntry) {
        derivedStateOf { navBackStackEntry?.destination?.route }
    }


    val navigationItem by remember {
        derivedStateOf {
            navigationItemList.find {
                it.route == currentRoute
            }
        }
    }

    val isMainScreenVisible by remember(isMediumExpendedWSC) {
        derivedStateOf {
            navigationItem != null
        }
    }

    val isBottomBarVisible by remember(isMediumExpendedWSC) {
        derivedStateOf {
            if (!isMediumExpendedWSC) {
                navigationItem != null
            } else {
                false
            }
        }
    }
    Row {
        AnimatedVisibility(
            modifier = Modifier.background(MaterialTheme.colorScheme.onSurface),
            visible = isMediumExpendedWSC && isMainScreenVisible,
            enter = slideInHorizontally(
                initialOffsetX = { fullWidth ->
                    -fullWidth
                }
            ),
            exit = slideOutHorizontally(
                targetOffsetX = { fullWidth ->
                    -fullWidth
                }
            )
        ) {
            NavigationSideBAr(
                navigationItemList = navigationItemList,
                currentRoute = currentRoute,
                onItemClick = { currentSideNavItem ->
                    rootNavController.navigate(currentSideNavItem.route) {

                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        rootNavController.graph.startDestinationRoute?.let {
                            // Pop up to the start destination, clearing the back stack
                            popUpTo(it) {
                                // Save the state of popped destinations
                                saveState = true
                            }
                            // Configure navigation to avoid multiple instances of the same destination
                            launchSingleTop = true
                            // Restore state when re-selecting a previously selected item
                            restoreState = true
                        }
                    }

                }
            )
        }
        Scaffold(
            bottomBar = {
                AnimatedVisibility(
                    visible = isBottomBarVisible,
                    enter = slideInVertically(
                        initialOffsetY = { fullHeight -> fullHeight },
                    ),
                    exit = slideOutVertically(
                        targetOffsetY = { fullHeight -> fullHeight }
                    )
                ) {
                    NewsBottomNavigationBar(bottomNavItemList = navigationItemList,
                        currentRoute = currentRoute,
                        onItemClick = { currentBottomNavItem ->
                            rootNavController.navigate(currentBottomNavItem.route) {

                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                rootNavController.graph.startDestinationRoute?.let {
                                    // Pop up to the start destination, clearing the back stack
                                    popUpTo(it) {
                                        // Save the state of popped destinations
                                        saveState = true
                                    }
                                    // Configure navigation to avoid multiple instances of the same destination
                                    launchSingleTop = true
                                    // Restore state when re-selecting a previously selected item
                                    restoreState = true
                                }
                            }
                        })
                }

            }) {
            RootNavGraph(settingViewModel, rootNavController, it)
        }
    }
}