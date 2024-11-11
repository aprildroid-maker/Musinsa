package com.musinsa.mobile.data.response

import android.annotation.SuppressLint
import com.musinsa.mobile.domain.model.Header
import com.musinsa.mobile.domain.model.base.DomainMapper
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
) : DomainMapper<Header> {

    override fun toDomainModel(): Header {
        return Header(
            title = title,
            iconUrl = iconUrl,
            linkUrl = linkUrl
        )
    }
}
