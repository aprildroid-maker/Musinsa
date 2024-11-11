package com.musinsa.mobile.data.datasource

import com.musinsa.mobile.data.response.HomeResponse
import com.musinsa.mobile.data.service.HomeService
import javax.inject.Inject

class DefaultHomeDataSource @Inject constructor(
    private val homeService: HomeService,
) : HomeDataSource {
    override suspend fun getHomeList(): List<HomeResponse> {
        return homeService.homeList().data
    }
}