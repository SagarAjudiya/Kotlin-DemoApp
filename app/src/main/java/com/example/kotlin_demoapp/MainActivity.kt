package com.example.kotlin_demoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.view.View.OnClickListener
import com.example.kotlin_demoapp.screens.AccountSecurity
import com.example.kotlin_demoapp.activity.ConstraintLayout
import com.example.kotlin_demoapp.activity.LinearLayout
import com.example.kotlin_demoapp.activity.PlaceholderActivity
import com.example.kotlin_demoapp.activity.RelativeLayout
import com.example.kotlin_demoapp.activity.components.ComponentActivity
import com.example.kotlin_demoapp.databinding.ActivityMainBinding
import com.example.kotlin_demoapp.screens.ParkingCitation
import com.example.kotlin_demoapp.screens.go_tour.GoTourLogin
import com.example.kotlin_demoapp.screens.on_cloud.activity.OnCloudActivity

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnLinearLayout.setOnClickListener(this)
        binding.btnRelativeLayout.setOnClickListener(this)
        binding.btnConstraintLayout.setOnClickListener(this)
        binding.placeholder.setOnClickListener(this)
        binding.btnComponent.setOnClickListener(this)
        binding.btnSecurity.setOnClickListener(this)
        binding.btnGoTour.setOnClickListener(this)
        binding.btnParkingCitation.setOnClickListener(this)
        binding.btnOnCloud.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.btnLinearLayout.id -> startActivity(Intent(this, LinearLayout::class.java))
            binding.btnRelativeLayout.id -> startActivity(Intent(this, RelativeLayout::class.java))
            binding.btnConstraintLayout.id -> startActivity(Intent(this, ConstraintLayout::class.java))
            binding.placeholder.id -> startActivity(Intent(this, PlaceholderActivity::class.java))
            binding.btnComponent.id -> startActivity(Intent(this, ComponentActivity::class.java))
            binding.btnSecurity.id -> startActivity(Intent(this, AccountSecurity::class.java))
            binding.btnGoTour.id -> startActivity(Intent(this, GoTourLogin::class.java))
            binding.btnParkingCitation.id -> startActivity(Intent(this, ParkingCitation::class.java))
            binding.btnOnCloud.id -> startActivity(Intent(this, OnCloudActivity::class.java))
        }
    }
}