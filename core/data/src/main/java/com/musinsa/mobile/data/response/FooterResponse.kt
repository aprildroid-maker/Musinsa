package com.musinsa.mobile.data.response

import android.annotation.SuppressLint
import com.musinsa.mobile.domain.model.Footer
import com.musinsa.mobile.domain.model.FooterType
import com.musinsa.mobile.domain.model.base.DomainMapper
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class FooterResponse(
    @SerialName("title")
    val title: String?,
    @SerialName("iconURL")
    val iconUrl: String?,
    @SerialName("type")
    val type: String?
) : DomainMapper<Footer> {

    override fun toDomainModel(): Footer {
        return Footer(
            title = title,
            iconUrl = iconUrl,
            type = type?.let { FooterType.from(it) }
        )
    }
}
