package com.musinsa.mobile.domain.model

import com.musinsa.mobile.domain.model.base.DomainModel

data class Header(
    val title: String?,
    val iconUrl: String?,
    val linkUrl: String?
) : DomainModel
