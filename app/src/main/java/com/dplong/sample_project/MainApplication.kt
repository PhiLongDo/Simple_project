package com.dplong.sample_project

import android.app.Application
import com.dplong.sample_project.data.pref.AppPreferences

open class MainApplication : Application() {
    override fun onCreate() {
        val sharedPreferences =
            this.getSharedPreferences("Simple_Preferences", MODE_PRIVATE)
        AppPreferences.init(sharedPreferences)
        super.onCreate()
    }
}