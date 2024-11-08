package com.musinsa.mobile.data.di

import com.musinsa.mobile.data.datasource.DefaultHomeDataSource
import com.musinsa.mobile.data.datasource.HomeDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Singleton
    @Binds
    fun bindsHomeDataSource(
        homeDataSource: DefaultHomeDataSource
    ) : HomeDataSource

}