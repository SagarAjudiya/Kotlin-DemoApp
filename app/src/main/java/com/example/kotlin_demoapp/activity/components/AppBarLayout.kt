package com.example.kotlin_demoapp.activity.components

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.databinding.ActivityAppBarLayoutBinding

class AppBarLayout : AppCompatActivity() {

    private lateinit var binding: ActivityAppBarLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAppBarLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }
}