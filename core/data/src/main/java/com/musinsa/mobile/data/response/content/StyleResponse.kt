package com.musinsa.mobile.data.response.content

import android.annotation.SuppressLint
import com.musinsa.mobile.domain.model.base.DomainMapper
import com.musinsa.mobile.domain.model.content.Style
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class StyleResponse(
    @SerialName("thumbnailURL")
    val thumbnailUrl: String?,
    @SerialName("linkURL")
    val linkUrl: String?
) : DomainMapper<Style> {

    override fun toDomainModel(): Style {
        return Style(
            thumbnailUrl = thumbnailUrl,
            linkUrl = linkUrl
        )
    }
}
