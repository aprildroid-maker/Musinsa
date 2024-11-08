package com.musinsa.mobile.data.di

import com.musinsa.mobile.data.service.HomeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun providesHomeService(
        retrofit: Retrofit
    ) : HomeService {
        return retrofit.create(HomeService::class.java)
    }
}