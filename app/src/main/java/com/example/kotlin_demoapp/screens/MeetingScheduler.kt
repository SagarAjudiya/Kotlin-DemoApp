package com.example.kotlin_demoapp.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlin_demoapp.databinding.ActivityMeetingSchedulerBinding

class MeetingScheduler : AppCompatActivity() {

    private lateinit var binding: ActivityMeetingSchedulerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMeetingSchedulerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }
}