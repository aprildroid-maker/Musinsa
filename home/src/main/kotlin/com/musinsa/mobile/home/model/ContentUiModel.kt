package com.musinsa.mobile.home.model

import androidx.compose.runtime.Immutable
import com.musinsa.mobile.domain.model.Content
import com.musinsa.mobile.domain.model.ContentType
import com.musinsa.mobile.domain.model.content.Banner
import com.musinsa.mobile.domain.model.content.Good
import com.musinsa.mobile.domain.model.content.Style

sealed interface ContentUiModel {

    @Immutable
    data class BannerUiModel(
        val title: String?,
        val keyword: String?,
        val description: String?,
        val thumbnailUrl: String?,
        val linkUrl: String?
    ) : ContentUiModel

    @Immutable
    data class GoodUiModel(
        val brandName: String?,
        val price: Long?,
        val saleRate: Int?,
        val thumbnailUrl: String?,
        val linkUrl: String?,
        val hasCoupon: Boolean?
    ) : ContentUiModel

    @Immutable
    data class StyleUiModel(
        val thumbnailUrl: String?,
        val linkUrl: String?
    ) : ContentUiModel

    companion object {
        fun fromContent(content: Content): List<ContentUiModel?> {
            return when (content.type) {
                ContentType.BANNER -> content.banners?.map { fromDomainModel(it) } ?: emptyList()
                ContentType.GRID -> content.goods?.take(6)?.map { fromDomainModel(it) } ?: emptyList()
                ContentType.SCROLL -> content.goods?.map { fromDomainModel(it) } ?: emptyList()
                ContentType.STYLE -> content.styles?.take(6)?.map { fromDomainModel(it) } ?: emptyList()
                else -> emptyList()
            }
        }

        fun fromDomainModel(model: Any?): ContentUiModel? {
            return when (model) {
                is Banner -> BannerUiModel(
                    title = model.title,
                    keyword = model.keyword,
                    description = model.description,
                    thumbnailUrl = model.thumbnailUrl,
                    linkUrl = model.linkUrl
                )

                is Good -> GoodUiModel(
                    brandName = model.brandName,
                    price = model.price,
                    saleRate = model.saleRate,
                    thumbnailUrl = model.thumbnailUrl,
                    linkUrl = model.linkUrl,
                    hasCoupon = model.hasCoupon
                )

                is Style -> StyleUiModel(
                    thumbnailUrl = model.thumbnailUrl,
                    linkUrl = model.linkUrl
                )

                else -> null
            }
        }
    }
}
