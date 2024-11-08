package com.musinsa.mobile.data.response.content

import android.annotation.SuppressLint
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class GoodResponse(
    @SerialName("brandName")
    val brandName: String?,
    @SerialName("price")
    val price: Long?,
    @SerialName("saleRate")
    val saleRate: Int?,
    @SerialName("thumbnailURL")
    val thumbnailUrl: String?,
    @SerialName("linkURL")
    val linkUrl: String?,
    @SerialName("hasCoupon")
    val hasCoupon: Boolean?
)
