package com.musinsa.mobile.domain.model

import com.musinsa.mobile.domain.model.base.DomainModel
import com.musinsa.mobile.domain.model.content.Banner
import com.musinsa.mobile.domain.model.content.Good
import com.musinsa.mobile.domain.model.content.Style


data class Content(
    val type: String?,
    val banners: List<Banner>?,
    val goods: List<Good>?,
    val styles: List<Style>?
) : DomainModel
