package com.example.kotlin_demoapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.kotlin_demoapp.databinding.LinearLayoutBinding

class LinearLayout : AppCompatActivity() {

    private lateinit var binding: LinearLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LinearLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.topLeft.setOnClickListener {
            Toast.makeText(this, "Top Left Tapped", Toast.LENGTH_SHORT).show()
        }
    }
}