package com.musinsa.mobile.home.content

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.musinsa.mobile.home.model.ContentUiModel

@Composable
internal fun Goods(
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
internal fun Styles(
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