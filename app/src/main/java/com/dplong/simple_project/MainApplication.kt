package com.dplong.simple_project

import android.app.Application
import com.dplong.simple_project.data.pref.AppPreferences

open class MainApplication : Application() {
    override fun onCreate() {
        val sharedPreferences = this.getSharedPreferences("Simple_Preferences", MODE_PRIVATE)
        AppPreferences.init(sharedPreferences)
        super.onCreate()
    }
}