package com.musinsa.mobile.data.service

import com.musinsa.mobile.data.response.HomeResponse
import com.musinsa.mobile.data.response.ResponseWrapper
import retrofit2.http.GET

interface HomeService {
    @GET("interview/list.json")
    suspend fun homeList() : ResponseWrapper<List<HomeResponse>>
}