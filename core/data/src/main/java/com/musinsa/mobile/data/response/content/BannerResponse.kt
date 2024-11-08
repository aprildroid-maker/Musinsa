package com.musinsa.mobile.data.response.content

import android.annotation.SuppressLint
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class BannerResponse(
    @SerialName("title")
    val title: String?,
    @SerialName("keyword")
    val keyword: String?,
    @SerialName("description")
    val description: String?,
    @SerialName("thumbnailURL")
    val thumbnailUrl: String?,
    @SerialName("linkURL")
    val linkUrl: String?
)
