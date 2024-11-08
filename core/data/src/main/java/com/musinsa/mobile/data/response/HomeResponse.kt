package com.musinsa.mobile.data.response

import android.annotation.SuppressLint
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class HomeResponse(
    @SerialName("header")
    val header: HeaderResponse?,
    @SerialName("contents")
    val contents: ContentResponse?,
    @SerialName("footer")
    val footer: FooterResponse?,
)
