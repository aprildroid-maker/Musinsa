/**
 * @author Daewon on 11,11월,2024
 *
 */

@file:OptIn(ExperimentalFoundationApi::class, ObsoleteCoroutinesApi::class)

package com.musinsa.mobile.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.musinsa.mobile.domain.model.ContentType
import com.musinsa.mobile.home.model.ContentUiModel
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel()
) {
    val uiState by viewModel.homeUiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        val paddingModifier = remember { modifier.padding(innerPadding) }

        when (uiState) {
            is HomeUiState.Loading -> HomeScreenLoading(modifier = paddingModifier)
            is HomeUiState.Error -> HomeScreenError(modifier = paddingModifier)
            is HomeUiState.Success -> HomeScreenLoaded(
                modifier = paddingModifier,
                uiState = uiState as HomeUiState.Success
            )
        }
    }
}

@Composable
private fun HomeScreenError(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(imageVector = Icons.Default.Info, contentDescription = "Error")
            Text("오류가 발생했습니다.")
        }
    }
}

@Composable
private fun HomeScreenLoading(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun HomeScreenLoaded(
    modifier: Modifier = Modifier,
    uiState: HomeUiState.Success
) {
    val listState = rememberLazyListState()
    var headerState by remember { mutableStateOf("") }

    LazyColumn(
        state = listState
    ) {
        stickyHeader {
            AnimatedContent(
                targetState = headerState,
                transitionSpec = {
                    if (targetState.isEmpty()) {
                        slideInVertically() togetherWith slideOutVertically()
                    } else {
                        slideInVertically() togetherWith slideOutVertically()
                    }
                },
                label = "Header"
            ) { title ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp, horizontal = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = title,
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Spacer(modifier = Modifier)
                    TextButton(
                        onClick = { }
                    ) {
                        Text("전체")
                    }
                }

            }
        }

        items(items = uiState.data) { item ->
            headerState = item.header?.title.orEmpty()

            when (item.type) {
                ContentType.BANNER -> Banners(
                    modifier = modifier,
                    banners = item.contents.filterIsInstance<ContentUiModel.BannerUiModel>()
                )

                ContentType.GRID -> Goods(
                    modifier = modifier,
                    goods = item.contents.filterIsInstance<ContentUiModel.GoodUiModel>()
                )

                ContentType.STYLE -> Styles(
                    modifier = modifier,
                    styles = item.contents.filterIsInstance<ContentUiModel.StyleUiModel>()
                )

                else -> {}
            }
        }
    }
}

@Composable
private fun Banners(
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

    HorizontalPager(
        modifier = modifier.fillMaxWidth(),
        state = bannerPagerState,
        beyondViewportPageCount = 1
    ) { page ->
        AsyncImage(
            modifier = Modifier.fillMaxWidth(),
            model = banners[page % pageSize].thumbnailUrl,
            contentScale = ContentScale.Crop,
            contentDescription = "Banner"
        )
    }

    LaunchedEffect(key1 = Unit) {
        if (pageSize > 1) {
            bannerPagerState.interactionSource.interactions
                .onStart { emit(DragInteraction.Start()) }
                .collectLatest {
                    ticker(
                        delayMillis = 3000L,
                        initialDelayMillis = 4000L,
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

@Composable
private fun Goods(
    modifier: Modifier = Modifier,
    goods: List<ContentUiModel.GoodUiModel>
) {
    LazyRow(
        modifier = modifier.fillMaxWidth()
    ) {
        items(goods) {
            AsyncImage(
                modifier = Modifier.size(100.dp),
                model = it.thumbnailUrl,
                contentScale = ContentScale.Crop,
                contentDescription = "Goods",
            )
        }
    }
}

@Composable
private fun Styles(
    modifier: Modifier = Modifier,
    styles: List<ContentUiModel.StyleUiModel>
) {
    LazyRow(
        modifier = modifier.fillMaxWidth()
    ) {
        items(styles) {
            AsyncImage(
                modifier = Modifier.size(100.dp),
                model = it.thumbnailUrl,
                contentScale = ContentScale.Crop,
                contentDescription = "Goods",
            )
        }
    }
}
