package com.musinsa.mobile.domain.model

import com.musinsa.mobile.domain.model.base.DomainModel
import com.musinsa.mobile.domain.model.content.Banner
import com.musinsa.mobile.domain.model.content.Good
import com.musinsa.mobile.domain.model.content.Style


data class Content(
    val type: ContentType?,
    val banners: List<Banner>?,
    val goods: List<Good>?,
    val styles: List<Style>?
) : DomainModel

enum class ContentType(val value: String) {
    BANNER("BANNER"),
    GRID("GRID"),
    SCROLL("SCROLL"),
    STYLE("STYLE");

    companion object {
        fun from(value: String): ContentType? {
            return entries.firstOrNull { it.value == value }
        }
    }
}
