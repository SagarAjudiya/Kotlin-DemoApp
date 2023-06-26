package com.example.kotlin_demoapp.activity.components

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.databinding.ActivityButtonBinding

class Button : AppCompatActivity() {

    private lateinit var binding: ActivityButtonBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityButtonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNormal.setOnClickListener {
            binding.btnAction.isEnabled = !binding.btnAction.isEnabled
        }
    }
}