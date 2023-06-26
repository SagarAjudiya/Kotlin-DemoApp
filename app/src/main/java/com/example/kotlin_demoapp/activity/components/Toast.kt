package com.example.kotlin_demoapp.activity.components

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.databinding.ActivityToastBinding
import java.time.Duration

class Toast : AppCompatActivity() {

    private lateinit var binding: ActivityToastBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityToastBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNormalToast.setOnClickListener {
            Toast.makeText(this,"Normal Toast",Toast.LENGTH_SHORT).show()
        }

        binding.btnCenterToast.setOnClickListener {
            Toast.makeText(this,"Toast with gravity",Toast.LENGTH_SHORT).apply {
                setGravity(Gravity.CENTER,0,0)
                show()
            }
        }

        binding.btnCustomToast.setOnClickListener {
            Toast(this).apply {
                view = View.inflate(this@Toast,R.layout.custom_toast,findViewById(R.id.customLayout))
                duration = Toast.LENGTH_SHORT
                setGravity(Gravity.CENTER,0,0)
                show()
            }
        }
    }
}