package com.musinsa.mobile.domain.model.base

interface DomainMapper<T : DomainModel?> {
    fun toDomainModel(): T
}