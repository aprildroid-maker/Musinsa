package com.musinsa.mobile.data.response.content

import android.annotation.SuppressLint
import com.musinsa.mobile.domain.model.base.DomainMapper
import com.musinsa.mobile.domain.model.content.Good
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
) : DomainMapper<Good> {

    override fun toDomainModel(): Good {
        return Good(
            brandName = brandName,
            price = price,
            saleRate = saleRate,
            thumbnailUrl = thumbnailUrl,
            linkUrl = linkUrl,
            hasCoupon = hasCoupon
        )
    }
}
