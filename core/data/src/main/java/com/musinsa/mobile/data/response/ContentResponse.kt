package com.musinsa.mobile.data.response

import android.annotation.SuppressLint
import com.musinsa.mobile.data.response.content.BannerResponse
import com.musinsa.mobile.data.response.content.GoodResponse
import com.musinsa.mobile.data.response.content.StyleResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class ContentResponse(
    @SerialName("type")
    val type: String?,
    @SerialName("banners")
    val banners: List<BannerResponse>?,
    @SerialName("goods")
    val goods: List<GoodResponse>?,
    @SerialName("styles")
    val styles: List<StyleResponse>?
)
