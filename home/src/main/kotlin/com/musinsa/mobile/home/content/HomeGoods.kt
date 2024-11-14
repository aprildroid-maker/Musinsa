package com.musinsa.mobile.home.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.musinsa.mobile.designsystem.contents.ProductImage
import com.musinsa.mobile.designsystem.contents.ProductItem
import com.musinsa.mobile.home.model.ContentUiModel

@Composable
internal fun GridGoods(
    modifier: Modifier = Modifier,
    goods: List<ContentUiModel.GoodUiModel>,
    onClick: (String?) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier
            .fillMaxWidth()
            .heightIn(max = 1200.dp),
        contentPadding = PaddingValues(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(goods) { item ->
            ProductItem(
                brandName = item.brandName,
                price = item.price,
                saleRate = item.saleRate,
                thumbnailUrl = item.thumbnailUrl,
                linkUrl = item.linkUrl,
                hasCoupon = item.hasCoupon == true,
                onClick = onClick
            )
        }
    }
}

@Composable
internal fun ScrollGoods(
    modifier: Modifier = Modifier,
    styles: List<ContentUiModel.GoodUiModel>,
    onClick: (String?) -> Unit
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        contentPadding = PaddingValues(horizontal = 12.dp)
    ) {
        items(styles) {
            ProductItem(
                modifier = Modifier.width(120.dp),
                brandName = it.brandName,
                price = it.price,
                saleRate = it.saleRate,
                thumbnailUrl = it.thumbnailUrl,
                linkUrl = it.linkUrl,
                hasCoupon = it.hasCoupon == true,
                onClick = onClick
            )
        }
    }
}

@Composable
internal fun StyleGoods(
    modifier: Modifier = Modifier,
    styles: List<ContentUiModel.StyleUiModel>,
    onClick: (String?) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier
            .fillMaxWidth()
            .heightIn(max = 5000.dp),
        contentPadding = PaddingValues(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        styles.forEachIndexed { index, item ->
            when (index) {
                0 -> {
                    item(span = { GridItemSpan(2) }) {
                        createProductImage(item, onClick).invoke()
                    }
                }

                1 -> {
                    val nextItem = styles.getOrNull(index + 1)
                    item {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            createProductImage(item, onClick).invoke()
                            if (nextItem != null) {
                                createProductImage(nextItem, onClick).invoke()
                            }
                        }
                    }
                }

                2 -> return@forEachIndexed
                else -> {
                    item {
                        createProductImage(item, onClick).invoke()
                    }
                }
            }
        }
    }
}

private fun createProductImage(
    item: ContentUiModel.StyleUiModel,
    onClick: (String?) -> Unit
): @Composable () -> Unit = {
    ProductImage(
        modifier = Modifier.clickable(enabled = !item.linkUrl.isNullOrEmpty()) {
            onClick(item.linkUrl)
        },
        thumbnailUrl = item.thumbnailUrl,
        hasCoupon = false
    )
}
