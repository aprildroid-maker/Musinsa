package com.musinsa.mobile.domain.model.content

import com.musinsa.mobile.domain.model.base.DomainModel

data class Good(
    val brandName: String?,
    val price: Long?,
    val saleRate: Int?,
    val thumbnailUrl: String?,
    val linkUrl: String?,
    val hasCoupon: Boolean?
) : DomainModel
