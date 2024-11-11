package com.musinsa.mobile.domain.model

import com.musinsa.mobile.domain.model.base.DomainModel

data class Home(
    val header: Header?,
    val contents: Content?,
    val footer: Footer?,
) : DomainModel
