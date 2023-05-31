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
        val view = binding.root
        setContentView(view)

        binding.datePicker.setOnClickListener {
            var datePicker = MaterialDatePicker.Builder.datePicker().setTitleText("Select Date").build().show(supportFragmentManager,"tag")
        }

        binding.timePicker.setOnClickListener {
//            var timePicker = MaterialDatePicker.Builder.().setTitleText("Select Date").build()
        }
    }
}