package com.musinsa.mobile.data.di

import com.musinsa.mobile.data.repository.DefaultHomeRepository
import com.musinsa.mobile.domain.repository.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindsHomeRepository(
        homeRepository: DefaultHomeRepository
    ) : HomeRepository
}
