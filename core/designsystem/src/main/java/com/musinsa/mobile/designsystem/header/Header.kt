package com.musinsa.mobile.designsystem.header

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun HomeHeader(
    modifier: Modifier = Modifier,
    title: String?,
    linkUrl: String?,
    iconUrl: String?,
    onClick: (() -> Unit)? = null
) {
    if (!title.isNullOrBlank() || !linkUrl.isNullOrBlank() || !iconUrl.isNullOrBlank()) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .padding(horizontal = 8.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (!title.isNullOrBlank()) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
            }
            if (!iconUrl.isNullOrBlank()) {
                AsyncImage(
                    model = iconUrl,
                    contentDescription = "header_icon"
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            if (!linkUrl.isNullOrBlank()) {
                TextButton(
                    onClick = onClick ?: { }
                ) {
                    Text(
                        text = "전체",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewHeader(modifier: Modifier = Modifier) {
    HomeHeader(
        modifier = modifier,
        title = "최신 상품",
        linkUrl = null,
        iconUrl = null,
        onClick = { }
    )
}