package com.example.kotlin_demoapp.activity.components

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.CheckBox
import android.widget.RadioButton
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.databinding.ActivityRadioCheckBinding

class RadioCheck : AppCompatActivity() {

    private lateinit var binding: ActivityRadioCheckBinding
    private lateinit var hobby: MutableList<CheckBox>
    private lateinit var hobbyStatus: HashMap<CheckBox, Boolean>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRadioCheckBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hobby = mutableListOf(
            binding.readingCheck,
            binding.moviesCheck,
            binding.listenCheck,
            binding.playingCheck
        )
        hobbyStatus = hashMapOf(
            binding.readingCheck to false,
            binding.moviesCheck to false,
            binding.listenCheck to false,
            binding.playingCheck to false
        )

        binding.genderGroup.setOnCheckedChangeListener { _, checkedId ->
            Log.d("radio", checkedId.toString())
            binding.selectedButton.text = findViewById<RadioButton>(checkedId).text.toString()
        }

        binding.clearRadioButton.setOnClickListener {
            findViewById<RadioButton>(binding.genderGroup.checkedRadioButtonId)?.isChecked = false
            binding.selectedButton.text = getString(R.string.not_selected)
        }

        binding.readingCheck.setOnCheckedChangeListener { _, isChecked ->
            hobbyStatus[binding.readingCheck] = isChecked
            updateHobbyStatus()
        }

        binding.listenCheck.setOnCheckedChangeListener { _, isChecked ->
            hobbyStatus[binding.listenCheck] = isChecked
            updateHobbyStatus()
        }

        binding.moviesCheck.setOnCheckedChangeListener { _, isChecked ->
            hobbyStatus[binding.moviesCheck] = isChecked
            updateHobbyStatus()
        }

        binding.playingCheck.setOnCheckedChangeListener { _, isChecked ->
            hobbyStatus[binding.playingCheck] = isChecked
            updateHobbyStatus()
        }

        binding.clearCheckBox.setOnClickListener {
            hobby.forEach {
                it.isChecked = false
            }
            binding.txtSelectCheckBox.text = getString(R.string.not_selected)
        }
    }

    private fun updateHobbyStatus() {
        var result = ""
        hobby.forEach {
            if (it.isChecked) {
                result += it.text.toString() + "\n"
            }
        }
        binding.txtSelectCheckBox.text = result
    }
}