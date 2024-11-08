package com.musinsa.mobile.data

import com.musinsa.mobile.data.di.ServiceModule
import com.musinsa.mobile.data.service.HomeService
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import retrofit2.Retrofit

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [ServiceModule::class]
)
internal class FakeServiceModule {
    @Provides
    fun providesHomeService(
        retrofit: Retrofit
    ) : HomeService {
        return retrofit.create(HomeService::class.java)
    }
}
