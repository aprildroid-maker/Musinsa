package com.musinsa.mobile.data.response

import android.annotation.SuppressLint
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class HeaderResponse(
    @SerialName("title")
    val title: String?,
    @SerialName("iconURL")
    val iconUrl: String?,
    @SerialName("linkURL")
    val linkUrl: String?
)
