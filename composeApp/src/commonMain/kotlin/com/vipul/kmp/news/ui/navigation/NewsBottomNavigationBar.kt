package com.vipul.kmp.news.ui.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun NewsBottomNavigationBar(
    bottomNavItemList: List<BottomNavigationItem>,
    currentRoute: String?,
    onItemClick: (BottomNavigationItem) -> Unit
) {

    NavigationBar(modifier = Modifier.fillMaxWidth()) {
        bottomNavItemList.forEach { bottomNavigationItem ->
            NavigationBarItem(
                selected = currentRoute == bottomNavigationItem.route,
                onClick = {
                    onItemClick(bottomNavigationItem)
                },
                icon = {
                    Icon(
                        painterResource(bottomNavigationItem.icon),
                        contentDescription = stringResource(bottomNavigationItem.title)
                    )
                },
                label = {
                    Text(
                        text = stringResource(bottomNavigationItem.title),
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Medium
                    )
                }
            )
        }
    }

}