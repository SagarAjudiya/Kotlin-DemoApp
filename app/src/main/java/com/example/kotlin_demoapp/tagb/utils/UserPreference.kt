package com.example.kotlin_demoapp.tagb.utils

import android.content.Context
import android.content.SharedPreferences

object UserPreference {
    private const val prefName = "KotlinDemoApp"
    private val shared: SharedPreferences =
        App.appContext.getSharedPreferences(prefName, Context.MODE_PRIVATE)
    private const val isUserLoginKey = "isUserLogin"
    private const val authTokenKey = "authToken"
    private const val refreshTokenKey = "refreshToken"
    private const val usernameKey = "username"
    private const val nameKey = "name"

    var isUserLogin: Boolean
        get() = shared.getBoolean(isUserLoginKey, false)
        set(value) = shared.edit().putBoolean(isUserLoginKey, value).apply()

    var authToken: String?
        get() = shared.getString(authTokenKey, "")
        set(value) = shared.edit().putString(authTokenKey, value).apply()

    var refreshToken: String?
        get() = shared.getString(refreshTokenKey, "")
        set(value) = shared.edit().putString(refreshTokenKey, value).apply()

    var username: String?
        get() = shared.getString(usernameKey, "")
        set(value) = shared.edit().putString(usernameKey, value).apply()

    var name: String?
        get() = shared.getString(nameKey, "")
        set(value) = shared.edit().putString(nameKey, value).apply()

    fun clearAll() {
        shared.edit().clear().apply()
    }
}