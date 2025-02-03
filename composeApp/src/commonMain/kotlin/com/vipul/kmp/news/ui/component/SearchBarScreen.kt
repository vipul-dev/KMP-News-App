package com.vipul.kmp.news.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import com.vipul.kmp.news.theme.mediumPadding
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.ic_search
import kmp_news_app.composeapp.generated.resources.search
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SearchBarScreen(
    text: String,
    onValueChange: (String) -> Unit,
    onSearch: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current
    TextField(
        value = text, onValueChange = {
            onValueChange(it)
        }, modifier = Modifier.fillMaxWidth().padding(horizontal = mediumPadding),

        textStyle = MaterialTheme.typography.bodyLarge.copy(
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface,
        ),
        leadingIcon = {
            Icon(painter = painterResource(Res.drawable.ic_search), contentDescription = null)
        },
        placeholder = {
            Text(
                text = stringResource(Res.string.search),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold
            )
        },
        shape = MaterialTheme.shapes.medium,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                focusManager.clearFocus()
                onSearch(text)
            }
        )
    )
}