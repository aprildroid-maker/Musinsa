package com.musinsa.mobile.data.datasource

import com.musinsa.mobile.data.response.HomeResponse

interface HomeDataSource {
    suspend fun getHomeList(): Result<List<HomeResponse>>
}