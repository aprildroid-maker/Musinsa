/**
 * @author Daewon on 11,11월,2024
 *
 */

@file:OptIn(ExperimentalFoundationApi::class, ObsoleteCoroutinesApi::class)

package com.musinsa.mobile.home

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.airbnb.mvrx.compose.collectAsState
import com.airbnb.mvrx.compose.mavericksViewModel
import com.musinsa.mobile.designsystem.footer.Footer
import com.musinsa.mobile.designsystem.header.Header
import com.musinsa.mobile.domain.model.ContentType
import com.musinsa.mobile.home.browser.launchCustomChromeTab
import com.musinsa.mobile.home.content.Banners
import com.musinsa.mobile.home.content.GridGoods
import com.musinsa.mobile.home.content.ScrollGoods
import com.musinsa.mobile.home.content.StyleGoods
import com.musinsa.mobile.home.model.ContentUiModel
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
                modifier = paddingModifier.fillMaxSize(),
                uiState = uiState as HomeUiState.Success,
                onClick = viewModel::fetch
            )
        }
    }
}

@Composable
private fun HomeScreenLoaded(
    modifier: Modifier = Modifier,
    uiState: HomeUiState.Success,
    onClick: (Int) -> Unit,
) {
    val context = LocalContext.current
    val backgroundColor = MaterialTheme.colorScheme.background.toArgb()

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        uiState.data.forEachIndexed { index, uiModel ->
            if (uiModel.header != null) {
                item {
                    Header(
                        title = uiModel.header.title,
                        linkUrl = uiModel.header.linkUrl,
                        iconUrl = uiModel.header.iconUrl,
                        onClick = { navigateToLink(context, it, backgroundColor) }
                    )
                }
            }

            item(contentType = { uiModel.type }) {
                when (uiModel.type) {
                    ContentType.BANNER -> Banners(
                        banners = uiModel.contents.filterIsInstance<ContentUiModel.BannerUiModel>(),
                        onClick = { navigateToLink(context, it, backgroundColor) }
                    )

                    ContentType.GRID -> GridGoods(
                        goods = uiModel.contents.filterIsInstance<ContentUiModel.GoodUiModel>(),
                        onClick = { navigateToLink(context, it, backgroundColor) }
                    )

                    ContentType.SCROLL -> ScrollGoods(
                        styles = uiModel.contents.filterIsInstance<ContentUiModel.GoodUiModel>(),
                        onClick = { navigateToLink(context, it, backgroundColor) }
                    )

                    ContentType.STYLE -> StyleGoods(
                        styles = uiModel.contents.filterIsInstance<ContentUiModel.StyleUiModel>(),
                        onClick = { navigateToLink(context, it, backgroundColor) }
                    )

                    else -> {}
                }
            }

            if (uiModel.footer != null) {
                item {
                    Box(
                        modifier = Modifier.padding(horizontal = 12.dp)
                    ) {
                        Footer(
                            modifier = Modifier.fillMaxWidth(),
                            text = uiModel.footer.title,
                            iconUrl = uiModel.footer.iconUrl,
                            onClick = { onClick(index) }
                        )
                    }
                }
            }
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
private fun HomeScreenError(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.onBackground)
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(imageVector = Icons.Default.Info, contentDescription = "Error")
            Text("오류가 발생했습니다.")
        }
    }
}

private fun navigateToLink(context: Context, url: String?, color: Int) {
    if (url.isNullOrBlank()) return
    launchCustomChromeTab(
        context = context,
        url = url,
        toolbarColor = color
    )
}
