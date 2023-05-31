package com.example.kotlin_demoapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlin_demoapp.databinding.ConstraintLayoutBinding

class ConstraintLayout : AppCompatActivity() {

    private lateinit var binding: ConstraintLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ConstraintLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }
}