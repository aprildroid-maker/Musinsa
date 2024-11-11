package com.musinsa.mobile.data.response

import android.annotation.SuppressLint
import com.musinsa.mobile.data.response.content.BannerResponse
import com.musinsa.mobile.data.response.content.GoodResponse
import com.musinsa.mobile.data.response.content.StyleResponse
import com.musinsa.mobile.domain.model.Content
import com.musinsa.mobile.domain.model.ContentType
import com.musinsa.mobile.domain.model.base.DomainMapper
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class ContentResponse(
    @SerialName("type")
    val type: String?,
    @SerialName("banners")
    val banners: List<BannerResponse>? = emptyList(),
    @SerialName("goods")
    val goods: List<GoodResponse>? = emptyList(),
    @SerialName("styles")
    val styles: List<StyleResponse>? = emptyList()
) : DomainMapper<Content> {

    override fun toDomainModel(): Content {
        return Content(
            type = type?.let { ContentType.from(it) },
            banners = banners?.map { it.toDomainModel() },
            goods = goods?.map { it.toDomainModel() },
            styles = styles?.map { it.toDomainModel() }
        )
    }
}
