package com.example.kotlin_demoapp.screens.on_cloud.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavHost
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.databinding.ActivityOnCloudBinding

class OnCloudActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnCloudBinding
    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.mainFragment) as? NavHostFragment)?.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOnCloudBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController?.let { binding.bottomNavView.setupWithNavController(it) }
    }
}