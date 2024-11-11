package com.musinsa.mobile.domain.repository

import com.musinsa.mobile.domain.model.Home

interface HomeRepository {
    suspend fun getHomeList(): Result<List<Home>>
}