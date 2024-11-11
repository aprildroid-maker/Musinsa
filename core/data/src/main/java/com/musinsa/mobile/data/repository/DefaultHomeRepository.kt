package com.musinsa.mobile.data.repository

import com.musinsa.mobile.data.datasource.HomeDataSource
import com.musinsa.mobile.domain.model.Home
import com.musinsa.mobile.domain.repository.HomeRepository
import javax.inject.Inject

class DefaultHomeRepository @Inject constructor(
    private val homeRemoteDataSource: HomeDataSource
) : HomeRepository {
    override suspend fun getHomeList(): Result<List<Home>> {
        return runCatching {
            homeRemoteDataSource.getHomeList().map {
                it.toDomainModel()
            }
        }
    }
}