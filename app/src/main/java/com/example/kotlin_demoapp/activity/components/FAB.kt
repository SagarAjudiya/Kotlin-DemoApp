package com.example.kotlin_demoapp.activity.components

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.databinding.ActivityFabBinding

class FAB : AppCompatActivity() {

    private lateinit var binding: ActivityFabBinding
    private var isFabEnable = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFabBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.extendedClear.visibility = View.GONE
        binding.txtClose.visibility = View.GONE
        binding.fabClose.visibility = View.GONE
        binding.customExtendedFAB.shrink()

        binding.customExtendedFAB.setOnClickListener {
            if (isFabEnable) {
                binding.extendedClear.hide()
                binding.fabClose.hide()
                binding.txtClose.visibility = View.GONE
                binding.customExtendedFAB.shrink()
                isFabEnable = false
            } else {
                binding.extendedClear.show()
                binding.fabClose.show()
                binding.txtClose.visibility = View.VISIBLE
                binding.customExtendedFAB.extend()
                isFabEnable = true
            }
        }

        binding.fabClose.setOnClickListener {
            binding.extendedClear.hide()
            binding.fabClose.hide()
            binding.txtClose.visibility = View.GONE
            binding.customExtendedFAB.shrink()
            isFabEnable = false
        }

        binding.extendedClear.setOnClickListener {
            Toast.makeText(this,"Clear Tapped",Toast.LENGTH_SHORT).show()
        }

        binding.smallButton.setOnClickListener {
            Toast.makeText(this,"Mini FAB",Toast.LENGTH_SHORT).show()
        }

        binding.mediumButton.setOnClickListener {
            Toast.makeText(this,"Normal FAB",Toast.LENGTH_SHORT).show()
        }

        binding.largeButton.setOnClickListener {
            Toast.makeText(this,"Large FAB",Toast.LENGTH_SHORT).show()
        }
    }
}