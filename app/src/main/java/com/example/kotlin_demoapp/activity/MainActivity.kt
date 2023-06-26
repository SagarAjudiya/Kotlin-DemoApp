package com.example.kotlin_demoapp.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin_demoapp.activity.components.ComponentActivity
import com.example.kotlin_demoapp.activity.intent.ImplicitIntent
import com.example.kotlin_demoapp.architecture.ViewModelActivity
import com.example.kotlin_demoapp.databinding.ActivityMainBinding
import com.example.kotlin_demoapp.screens.AccountSecurity
import com.example.kotlin_demoapp.screens.ParkingCitation
import com.example.kotlin_demoapp.screens.go_tour.GoTourLogin
import com.example.kotlin_demoapp.tagb.module.dashboard.view.WebServiceActivity
import com.example.kotlin_demoapp.tagb.helper.IntentKeys

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (intent.extras?.getBoolean(IntentKeys.FROM_NOTIFICATION) == true) {
            startActivity(Intent(this, ImplicitIntent::class.java))
        }
        binding.btnTimer.setOnClickListener(this)
        binding.btnPlaceholder.setOnClickListener(this)
        binding.btnComponent.setOnClickListener(this)
        binding.btnSecurity.setOnClickListener(this)
        binding.btnGoTour.setOnClickListener(this)
        binding.btnParkingCitation.setOnClickListener(this)
        binding.btnWebServices.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.btnTimer.id -> startActivity(Intent(this, ViewModelActivity::class.java))
            binding.btnPlaceholder.id -> startActivity(
                Intent(
                    this,
                    PlaceholderActivity::class.java
                )
            )

            binding.btnComponent.id -> startActivity(Intent(this, ComponentActivity::class.java))
            binding.btnSecurity.id -> startActivity(Intent(this, AccountSecurity::class.java))
            binding.btnGoTour.id -> startActivity(Intent(this, GoTourLogin::class.java))
            binding.btnParkingCitation.id -> startActivity(
                Intent(
                    this,
                    ParkingCitation::class.java
                )
            )

            binding.btnWebServices.id -> startActivity(Intent(this, WebServiceActivity::class.java))
        }
    }
}