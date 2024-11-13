package com.musinsa.mobile.home.model

import com.musinsa.mobile.domain.model.Footer
import com.musinsa.mobile.domain.model.FooterType

data class FooterUiModel(
    val type: FooterType?,
    val title: String?,
    val iconUrl: String?
) {
}
