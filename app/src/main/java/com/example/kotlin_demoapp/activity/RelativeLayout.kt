package com.example.kotlin_demoapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlin_demoapp.databinding.RelativeLayoutBinding

class RelativeLayout : AppCompatActivity() {

    private lateinit var binding: RelativeLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = RelativeLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.checkText.setOnClickListener {
            println("tapped")
        }
    }
}