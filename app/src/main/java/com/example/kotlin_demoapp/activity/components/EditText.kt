package com.example.kotlin_demoapp.activity.components

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import com.example.kotlin_demoapp.databinding.ActivityEdittextBinding

class EditText : AppCompatActivity() {

    private lateinit var binding: ActivityEdittextBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEdittextBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //TODO: Can not change outline of edittext

        binding.editTextTextEmailAddress.addTextChangedListener {
            val text = it.toString().trim()
            if (text.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(text).matches()) {
                binding.inputLayoutEmail.error = "Email is not valid!"
            } else {
                binding.inputLayoutEmail.error = null
            }
        }

        binding.editTextPostalAddress.setOnFocusChangeListener { view, b ->
            run {
                if (!b && binding.editTextPostalAddress.text.toString().trim().isEmpty()) {
                    binding.inputLayoutPostal.error = "required*"
                } else {
                    binding.inputLayoutPostal.error = null
                }
            }
        }

        binding.editTextPhone.doOnTextChanged { text, start, before, count ->
            if (count == 0 || count > 10) {
                binding.inputLayoutTextPhone.error = "Set correct phone number"
            } else {
                binding.inputLayoutTextPhone.error = null
            }
        }
    }
}