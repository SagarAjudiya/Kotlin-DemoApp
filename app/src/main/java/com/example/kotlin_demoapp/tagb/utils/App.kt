package com.example.kotlin_demoapp.tagb.utils

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    companion object {
        lateinit var appContext: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
    }
}