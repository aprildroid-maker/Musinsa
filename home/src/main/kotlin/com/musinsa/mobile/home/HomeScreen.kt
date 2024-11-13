/**
 * @author Daewon on 11,11월,2024
 *
 */

@file:OptIn(ExperimentalFoundationApi::class, ObsoleteCoroutinesApi::class)

package com.musinsa.mobile.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.airbnb.mvrx.compose.collectAsState
import com.airbnb.mvrx.compose.mavericksViewModel
import com.airbnb.mvrx.withState
import com.musinsa.mobile.designsystem.header.HomeHeader
import com.musinsa.mobile.domain.model.Content
import com.musinsa.mobile.domain.model.ContentType
import com.musinsa.mobile.home.content.Banners
import com.musinsa.mobile.home.content.GridGoods
import com.musinsa.mobile.home.content.ScrollGoods
import com.musinsa.mobile.home.model.ContentUiModel
import com.musinsa.mobile.home.model.HomeUiModel
import kotlinx.coroutines.ObsoleteCoroutinesApi

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = mavericksViewModel()
) {
    val uiState by viewModel.collectAsState()

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
    var headerState by remember { mutableStateOf<HomeUiModel?>(null) }

    LazyColumn(
        modifier = modifier,
        state = listState,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        stickyHeader {
            AnimatedContent(
                targetState = headerState,

                label = "Header"
            ) { headerState ->
                if (headerState?.header != null) {
                    HomeHeader(
                        title = headerState.header.title,
                        linkUrl = headerState.header.linkUrl,
                        iconUrl = headerState.header.iconUrl,
                        onClick = { }
                    )
                }
            }
        }

        items(items = uiState.data) { item ->
            when (item.type) {
                ContentType.BANNER -> Banners(
                    banners = item.contents.filterIsInstance<ContentUiModel.BannerUiModel>()
                )

                ContentType.GRID -> GridGoods(
                    goods = item.contents.filterIsInstance<ContentUiModel.GoodUiModel>()
                )

                ContentType.SCROLL -> ScrollGoods(
                    styles = item.contents.filterIsInstance<ContentUiModel.GoodUiModel>()
                )

                ContentType.STYLE -> ScrollGoods(
                    styles = item.contents.filterIsInstance<ContentUiModel.GoodUiModel>()
                )

                else -> {}
            }
        }
    }

    LaunchedEffect(Unit) {
        snapshotFlow { listState.firstVisibleItemIndex }.collect { index ->
            headerState = uiState.data[index]
        }
    }
}
