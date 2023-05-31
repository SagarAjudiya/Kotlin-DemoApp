package com.example.kotlin_demoapp.activity.components

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.databinding.ActivityGridLayoutBinding

class GridLayout : AppCompatActivity() {

    private lateinit var binding: ActivityGridLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGridLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


    }
}