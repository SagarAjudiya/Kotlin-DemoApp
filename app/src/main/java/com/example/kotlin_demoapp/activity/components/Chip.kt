package com.example.kotlin_demoapp.activity.components

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.databinding.ActivityChipBinding
import com.example.kotlin_demoapp.databinding.ActivityToastBinding

class Chip : AppCompatActivity() {

    private lateinit var binding: ActivityChipBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChipBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.customChip.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.customChip.apply {
                    setChipBackgroundColorResource(R.color.deepAqua)
                    setTextColor(getColor(R.color.white))
                    isCloseIconVisible = true
                    setChipStrokeColorResource(R.color.black)
                    setChipStrokeWidthResource(R.dimen.dp_one)
                }
                Toast.makeText(this,"Chip is Selected",Toast.LENGTH_SHORT).show()
            } else {
                binding.customChip.apply {
                    setChipBackgroundColorResource(R.color.grey)
                    setTextColor(getColor(R.color.black))
                    isCloseIconVisible = false
                    setChipStrokeColorResource(R.color.black)
                    setChipStrokeWidthResource(R.dimen.dp_zero)
                }
                Toast.makeText(this,"Deselected",Toast.LENGTH_SHORT).show()
            }
        }
    }
}