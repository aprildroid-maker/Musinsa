package com.musinsa.mobile.data.response.content

import android.annotation.SuppressLint
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class StyleResponse(
    @SerialName("thumbnailURL")
    val thumbnailUrl: String?,
    @SerialName("linkURL")
    val linkUrl: String?
)
