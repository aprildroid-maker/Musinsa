package com.musinsa.mobile.data.di

import com.musinsa.mobile.data.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideJsonConverter(): Converter.Factory {
        val json = Json {
            ignoreUnknownKeys = true
            explicitNulls = false
        }
        return json.asConverterFactory("application/json".toMediaType())
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor())
            retryOnConnectionFailure(true)
        }.build()
    }

    @Provides
    @Singleton
    fun provideImageRetrofit(
        okHttpClient: OkHttpClient,
        converter: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(converter)
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }
}
