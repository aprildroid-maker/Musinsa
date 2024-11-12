@file:OptIn(ObsoleteCoroutinesApi::class)

package com.musinsa.mobile.home.content

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.musinsa.mobile.designsystem.header.TextBanner
import com.musinsa.mobile.home.model.ContentUiModel
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart

@Composable
internal fun Banners(
    modifier: Modifier = Modifier,
    banners: List<ContentUiModel.BannerUiModel>
) {
    val pageSize = banners.size
    val pageCount = if (pageSize > 1) pageSize * 500 else pageSize
    val bannerPagerState = rememberPagerState(initialPage = pageCount / 2, pageCount = {
        pageCount
    })
    val bannerSpringAnimation = spring<Float>(
        dampingRatio = Spring.DampingRatioNoBouncy,
        stiffness = Spring.StiffnessLow
    )

    Box(modifier = modifier) {
        AnimatedContent(
            targetState = bannerPagerState.currentPage,
            transitionSpec = {
                fadeIn(animationSpec = bannerSpringAnimation).togetherWith(
                    fadeOut(animationSpec = bannerSpringAnimation)
                )
            },
            label = "bannerImage"
        ) { page ->
            val banner = banners[page % pageSize]
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                model = banner.thumbnailUrl,
                contentScale = ContentScale.Crop,
                contentDescription = "banner"
            )
        }

        HorizontalPager(
            state = bannerPagerState,
            beyondViewportPageCount = 1
        ) { page ->
            val banner = banners[page % pageSize]
            TextBanner(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .padding(bottom = 40.dp),
                title = banner.title,
                keyword = banner.keyword,
                description = banner.description,
            )
        }

        if (pageSize > 1) {
            Box(
                modifier = Modifier
                    .background(Color.LightGray.copy(0.7f))
                    .padding(horizontal = 12.dp, vertical = 2.dp)
                    .align(Alignment.BottomEnd),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${bannerPagerState.currentPage % pageSize + 1} / $pageSize",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }

    LaunchedEffect(key1 = Unit) {
        if (pageSize > 1) {
            bannerPagerState.interactionSource.interactions
                .onStart { emit(DragInteraction.Start()) }
                .collectLatest {
                    ticker(
                        delayMillis = 3000L,
                        initialDelayMillis = 3000L,
                    ).consumeEach {
                        bannerPagerState.animateScrollToPage(
                            page = bannerPagerState.currentPage + 1,
                            animationSpec = bannerSpringAnimation
                        )
                    }
                }
        }
    }
}