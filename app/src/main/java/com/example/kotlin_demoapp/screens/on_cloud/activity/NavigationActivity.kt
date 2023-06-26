package com.example.kotlin_demoapp.screens.on_cloud.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.databinding.ActivityNavigationBinding

class NavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNavigationBinding
    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.mainFragment) as? NavHostFragment)?.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController?.let { binding.bottomNavView.setupWithNavController(it) }

    }
}