package com.musinsa.mobile

import android.app.Application
import com.airbnb.mvrx.Mavericks
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MusinsaApplication : Application() {
    override fun onCreate() {
        init()
        super.onCreate()
    }

    private fun init() {
        Mavericks.initialize(this)
    }
}
