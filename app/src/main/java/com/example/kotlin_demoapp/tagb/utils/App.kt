package com.example.kotlin_demoapp.tagb.utils

import android.app.Application

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