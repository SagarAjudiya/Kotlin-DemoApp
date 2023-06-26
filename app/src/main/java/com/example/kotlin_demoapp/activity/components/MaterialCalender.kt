package com.example.kotlin_demoapp.activity.components

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlin_demoapp.databinding.ActivityMaterialCalenderBinding
import com.google.android.material.datepicker.MaterialDatePicker

class MaterialCalender : AppCompatActivity() {

    private lateinit var binding: ActivityMaterialCalenderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMaterialCalenderBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}