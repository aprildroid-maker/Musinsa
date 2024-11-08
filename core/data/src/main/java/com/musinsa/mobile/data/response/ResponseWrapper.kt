package com.musinsa.mobile.data.response

import android.annotation.SuppressLint
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class ResponseWrapper<T>(
    @SerialName("data")
    val data: T
)