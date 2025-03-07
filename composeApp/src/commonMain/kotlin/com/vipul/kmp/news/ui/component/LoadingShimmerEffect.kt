package com.vipul.kmp.news.ui.component

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.unit.dp
import com.vipul.kmp.news.theme.cardMinSize
import com.vipul.kmp.news.theme.imageSize
import com.vipul.kmp.news.theme.mediumPadding
import com.vipul.kmp.news.theme.shimmerColors
import com.vipul.kmp.news.theme.xLargePadding
import com.vipul.kmp.news.theme.xSmallPadding
import com.vipul.kmp.news.theme.xxLargePadding
import com.vipul.kmp.news.theme.xxSmallPadding
import com.vipul.kmp.news.theme.xxxLargePadding

@Composable
fun LoadingShimmerEffect() {
    val transition = rememberInfiniteTransition()
    val translateAnimation by transition.animateFloat(
        initialValue = 0f,
        targetValue = 400f,
        animationSpec = infiniteRepeatable(
            tween(1500, easing = LinearOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )


    val brush by remember {
        derivedStateOf {
            Brush.linearGradient(
                colors = shimmerColors,
                start = Offset(translateAnimation, translateAnimation),
                end = Offset(translateAnimation + 100f, translateAnimation + 100f),
                tileMode = TileMode.Mirror
            )
        }
    }



    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(cardMinSize),
        verticalItemSpacing = mediumPadding,
        horizontalArrangement = Arrangement.spacedBy(xLargePadding),
        contentPadding = PaddingValues(xLargePadding),
        userScrollEnabled = false
    ) {
        repeat(15) {
            item {
                ArticleShimmerEffectCard(brush)
            }
        }
    }
}

@Composable
fun ArticleShimmerEffectCard(brush: Brush) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(xSmallPadding)
    ) {
        Box(
            modifier = Modifier.size(imageSize).background(brush = brush, RoundedCornerShape(10.dp))
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(xxSmallPadding)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth().height(xxxLargePadding).background(brush = brush, RoundedCornerShape(10.dp))
            )
            Box(
                modifier =
                Modifier.fillMaxWidth().height(xxLargePadding).background(brush = brush, RoundedCornerShape(10.dp))
            )
            Box(
                modifier =
                Modifier.fillMaxWidth(0.4f).height(mediumPadding).background(brush = brush, RoundedCornerShape(10.dp))
            )
        }
    }
}

