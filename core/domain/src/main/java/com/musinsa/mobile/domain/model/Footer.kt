package com.musinsa.mobile.domain.model

import com.musinsa.mobile.domain.model.base.DomainModel

data class Footer(
    val title: String?,
    val iconUrl: String?,
    val type: String?
) : DomainModel
