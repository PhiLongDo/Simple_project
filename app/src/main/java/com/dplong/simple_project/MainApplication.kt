package com.dplong.simple_project

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}