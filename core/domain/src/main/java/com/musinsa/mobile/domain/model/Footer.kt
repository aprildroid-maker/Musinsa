package com.musinsa.mobile.domain.model

import com.musinsa.mobile.domain.model.base.DomainModel

data class Footer(
    val type: FooterType?,
    val title: String?,
    val iconUrl: String?
) : DomainModel

enum class FooterType {
    MORE,
    REFRESH;

    companion object {
        fun from(value: String): FooterType? {
            return entries.firstOrNull { it.name == value }
        }
    }
}
