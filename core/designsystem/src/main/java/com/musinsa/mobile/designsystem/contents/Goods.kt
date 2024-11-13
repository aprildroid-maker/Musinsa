package com.musinsa.mobile.designsystem.contents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import java.text.DecimalFormat

@Composable
fun ProductItem(
    modifier: Modifier = Modifier,
    brandName: String?,
    price: Long?,
    saleRate: Int?,
    thumbnailUrl: String?,
    linkUrl: String?,
    hasCoupon: Boolean,
    onClick: (String?) -> Unit,
) {
    Column(
        modifier = modifier.clickable { onClick(linkUrl) },
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        ProductImage(
            thumbnailUrl = thumbnailUrl,
            hasCoupon = hasCoupon
        )

        ProductInfo(
            brandName = brandName,
            price = price,
            saleRate = saleRate,
        )
    }
}

@Composable
private fun ProductImage(
    modifier: Modifier = Modifier,
    thumbnailUrl: String?,
    hasCoupon: Boolean
) {
    Box {
        AsyncImage(
            modifier = modifier.fillMaxWidth(),
            model = thumbnailUrl,
            contentDescription = "product_thumbnail",
            contentScale = ContentScale.Crop
        )

        // 쿠폰
        if (hasCoupon) {
            Surface(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .background(Color(0xFF145589))
                    .padding(4.dp),
                color = Color.Unspecified
            ) {
                Text(
                    text = "쿠폰",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
private fun ProductInfo(
    modifier: Modifier = Modifier,
    brandName: String?,
    price: Long?,
    saleRate: Int?,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        if (!brandName.isNullOrBlank()) {
            Text(
                text = brandName,
                style = MaterialTheme.typography.labelSmall,
                color = Color.Gray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (price != null) {
                Text(
                    text = getDecimalFormat(price),
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            if (saleRate != null) {
                Text(
                    text = "${saleRate}%",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color(0xE2FF4500),
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

private fun getDecimalFormat(price: Long): String {
    return DecimalFormat("#,###").format(price)
}
