package com.example.kotlin_demoapp.activity.components

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.databinding.ActivityImagebuttonBinding

class ImageButton : AppCompatActivity() {

    private lateinit var binding: ActivityImagebuttonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityImagebuttonBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.imageButton.setOnClickListener {
            Toast.makeText(this, "Image Picked !!", Toast.LENGTH_SHORT).show()
        }
    }
}