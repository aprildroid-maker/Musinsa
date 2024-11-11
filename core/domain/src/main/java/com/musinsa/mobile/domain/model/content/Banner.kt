package com.musinsa.mobile.domain.model.content

import com.musinsa.mobile.domain.model.base.DomainModel

data class Banner(
    val title: String?,
    val keyword: String?,
    val description: String?,
    val thumbnailUrl: String?,
    val linkUrl: String?
) : DomainModel
