@file:OptIn(ExperimentalLayoutApi::class)

package com.musinsa.mobile.home.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.musinsa.mobile.designsystem.contents.ProductItem
import com.musinsa.mobile.home.model.ContentUiModel

@Composable
internal fun GridGoods(
    modifier: Modifier = Modifier,
    goods: List<ContentUiModel.GoodUiModel>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier.fillMaxWidth().heightIn(max = 1200.dp),
        contentPadding = PaddingValues(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(goods) { item ->
            ProductItem(
                brandName = item.brandName,
                price = item.price,
                saleRate = item.saleRate,
                thumbnailUrl = item.thumbnailUrl,
                linkUrl = item.linkUrl,
                hasCoupon = item.hasCoupon == true,
                onClick = { }
            )
        }
    }
}

@Composable
internal fun ScrollGoods(
    modifier: Modifier = Modifier,
    styles: List<ContentUiModel.GoodUiModel>
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(styles) {
            ProductItem(
                brandName = it.brandName,
                price = it.price,
                saleRate = it.saleRate,
                thumbnailUrl = it.thumbnailUrl,
                linkUrl = it.linkUrl,
                hasCoupon = it.hasCoupon == true,
                onClick = { }
            )
        }
    }
}