package com.musinsa.mobile.data.response

import android.annotation.SuppressLint
import com.musinsa.mobile.domain.model.Home
import com.musinsa.mobile.domain.model.base.DomainMapper
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class HomeResponse(
    @SerialName("header")
    val header: HeaderResponse?,
    @SerialName("contents")
    val contents: ContentResponse?,
    @SerialName("footer")
    val footer: FooterResponse?,
) : DomainMapper<Home> {

    override fun toDomainModel(): Home {
        return Home(
            header = header?.toDomainModel(),
            contents = contents?.toDomainModel(),
            footer = footer?.toDomainModel()
        )
    }
}
