package com.example.kotlin_demoapp.tagb.module.authentication.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.tagb.helper.launchActivity
import com.example.kotlin_demoapp.tagb.helper.putDelay
import com.example.kotlin_demoapp.tagb.helper.setStatusBarLightAppearance
import com.example.kotlin_demoapp.tagb.module.dashboard.view.WebServiceActivity
import com.example.kotlin_demoapp.tagb.utils.UserPreference

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setStatusBarLightAppearance(true)
        putDelay(1000) {
            if (UserPreference.isUserLogin) {
                launchActivity<WebServiceActivity>()
                finish()
            } else {
                launchActivity<OnBoardActivity>()
                finish()
            }
        }
    }
}