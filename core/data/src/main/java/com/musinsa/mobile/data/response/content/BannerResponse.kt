package com.musinsa.mobile.data.response.content

import android.annotation.SuppressLint
import com.musinsa.mobile.domain.model.base.DomainMapper
import com.musinsa.mobile.domain.model.content.Banner
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
) : DomainMapper<Banner> {

    override fun toDomainModel(): Banner {
        return Banner(
            title = title,
            keyword = keyword,
            description = description,
            thumbnailUrl = thumbnailUrl,
            linkUrl = linkUrl
        )
    }
}
