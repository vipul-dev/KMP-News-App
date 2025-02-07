package com.vipul.kmp.news.ui.articleDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.vipul.kmp.news.models.Article
import com.vipul.kmp.news.theme.detailImageSize
import com.vipul.kmp.news.theme.xLargePadding
import com.vipul.kmp.news.utils.shareLink
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.article_detail
import kmp_news_app.composeapp.generated.resources.ic_bookmark_outline
import kmp_news_app.composeapp.generated.resources.ic_browse
import kmp_news_app.composeapp.generated.resources.ic_error
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleDetailScreen(
    navController: NavController,
    currentArticle: Article
) {
    val urlHandler = LocalUriHandler.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(Res.string.article_detail),
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(Res.string.article_detail)
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            shareLink(currentArticle.url)
                        }
                    ) {
                        Icon(
                            Icons.Filled.Share,
                            contentDescription = null
                        )
                    }
                    IconButton(
                        onClick = {
                            urlHandler.openUri(currentArticle.url)
                        }
                    ) {
                        Icon(
                            painterResource(Res.drawable.ic_browse),
                            contentDescription = null
                        )
                    }
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(
                            painterResource(Res.drawable.ic_bookmark_outline),
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            contentPadding = PaddingValues(xLargePadding),
            verticalArrangement = Arrangement.spacedBy(xLargePadding)
        ) {
            item {
                AsyncImage(
                    modifier = Modifier.fillMaxWidth().height(detailImageSize)
                        .clip(MaterialTheme.shapes.large).background(
                            Color.Gray
                        ),
                    model = currentArticle.urlToImage,
                    error = painterResource(Res.drawable.ic_error),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
            }

            item {
                Text(
                    text = currentArticle.title,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            currentArticle.description?.let {
                item {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
            currentArticle.publishedAt.let {
                item {
                    val dateAndTime = ("")
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }
    }
}