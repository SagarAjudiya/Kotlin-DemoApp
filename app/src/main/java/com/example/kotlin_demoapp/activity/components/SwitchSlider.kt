package com.example.kotlin_demoapp.activity.components

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.SeekBar
import android.widget.Toast
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.databinding.ActivitySwitchSliderBinding

class SwitchSlider : AppCompatActivity() {

    private lateinit var binding: ActivitySwitchSliderBinding
    private var previousProgress: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySwitchSliderBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.switchEnable.setOnCheckedChangeListener { _, isChecked ->
            when (isChecked) {
                true -> {
                    binding.seekBar.isEnabled = true
                    binding.seekBarDiscrete.isEnabled = true
                }

                false -> {
                    binding.seekBar.isEnabled = false
                    binding.seekBarDiscrete.isEnabled = false
                }
            }
        }

        binding.seekBar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                // write custom code for progress is changed
                binding.editableImage.apply {
                    layoutParams.width = progress * 10
                    layoutParams.height = progress * 10
                    requestLayout()
                }
                binding.seekBarDiscrete.progress = progress / 10
                binding.progressBar.progress = progress
                previousProgress = progress
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                // write custom code for progress is started
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                // write custom code for progress is stopped
                Toast.makeText(
                    this@SwitchSlider,
                    "Progress is: " + seek.progress + "%",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        )

        binding.seekBarDiscrete.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                // write custom code for progress is changed
                binding.editableImage.apply {
                    layoutParams.width = progress * 100
                    layoutParams.height = progress * 100
                    requestLayout()
                }
                binding.seekBar.progress = progress * 10
                binding.progressBar.progress = progress * 10
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                // write custom code for progress is started
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                // write custom code for progress is stopped
                Toast.makeText(
                    this@SwitchSlider,
                    "Progress is: " + seek.progress + "%",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        )

    }
}