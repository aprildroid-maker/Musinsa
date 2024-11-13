package com.musinsa.mobile.home.model

import androidx.compose.runtime.Immutable
import com.musinsa.mobile.domain.model.ContentType
import com.musinsa.mobile.domain.model.Footer
import com.musinsa.mobile.domain.model.FooterType
import com.musinsa.mobile.domain.model.Header
import com.musinsa.mobile.domain.model.Home

@Immutable
data class HomeUiModel(
    val type: ContentType?,
    val header: Header?,
    val contents: List<ContentUiModel?>,
    val footer: Footer?,
) {
    companion object {
        fun from(domainModel: Home): HomeUiModel {
            return HomeUiModel(
                type = domainModel.contents?.type,
                header = domainModel.header,
                contents = domainModel.contents?.let { ContentUiModel.fromContent(it) } ?: emptyList(),
                footer = domainModel.footer
            )
        }
    }
}
