package com.dplong.simple_project.data.pref

import android.content.SharedPreferences

class AppPreferences private constructor(sharedPreferences: SharedPreferences) {
    companion object {
        private const val SIMPLE_STRING_KEY = "simple string key"
        private const val SIMPLE_INT_KEY = "simple int key"
        private const val SIMPLE_LONG_KEY = "simple long key"
        private const val SIMPLE_BOOLEAN_KEY = "simple boolean key"
        private const val TOKEN_KEY = "token_key"
        private const val USERNAME_KEY = "username_key"

        @Volatile
        private var instance: AppPreferences? = null


        fun init(sharedPreferences: SharedPreferences) {
            instance = AppPreferences(sharedPreferences)
        }

        fun getInstance() = instance!!
    }

    private lateinit var sharedPreferences: SharedPreferences

    // Simple
    var stringValue by PreferencesDelegate<String, String>(
        preferences = sharedPreferences,
        name = SIMPLE_STRING_KEY,
        defValue = ""
    )
    var intValue by PreferencesDelegate<Int, Int>(
        preferences = sharedPreferences,
        name = SIMPLE_INT_KEY,
        defValue = 0
    )
    var longValue by PreferencesDelegate<Long, Long>(
        preferences = sharedPreferences,
        name = SIMPLE_LONG_KEY,
        defValue = 0
    )
    var booleanValue by PreferencesDelegate<Boolean, Boolean>(
        preferences = sharedPreferences,
        name = SIMPLE_BOOLEAN_KEY,
        defValue = false
    )

    // Credential
    var token by PreferencesDelegate<String, String>(
        preferences = sharedPreferences,
        name = TOKEN_KEY,
        defValue = ""
    )
    var username by PreferencesDelegate<String, String>(
        preferences = sharedPreferences,
        name = USERNAME_KEY,
        defValue = ""
    )
}