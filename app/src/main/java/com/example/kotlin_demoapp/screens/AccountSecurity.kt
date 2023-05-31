package com.example.kotlin_demoapp.screens

import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.doOnTextChanged
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.databinding.ActivityAccountSecurityBinding


class AccountSecurity : AppCompatActivity() {

    private lateinit var binding: ActivityAccountSecurityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAccountSecurityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.mainScroll.isVerticalScrollBarEnabled = false

        binding.imgBack.setOnClickListener {
            finish()
        }

        binding.btnEditEmail.setOnClickListener {
            val divider = binding.emailDivider.layoutParams as ConstraintLayout.LayoutParams
            divider.topMargin = resources.getDimensionPixelSize(R.dimen.dp_ten)
            binding.emailInputLayout.isEnabled = true
            binding.emailGroup.visibility = View.VISIBLE
            binding.btnEditEmail.visibility = View.GONE
        }

        binding.btnSetupAuth.setOnClickListener {
            binding.passcodeGroup.visibility = View.VISIBLE
            binding.btnSetupAuth.apply {
                setStrokeColorResource(R.color.skyBlue)
                setStrokeWidthResource(R.dimen.dp_one)
                setBackgroundColor(resources.getColor(R.color.white))
                setTextColor(resources.getColor(R.color.skyBlue))
                setIconTintResource(R.color.skyBlue)
                text = "CLECVXEJB374"
                icon = ResourcesCompat.getDrawable(resources, R.drawable.icon_copy, null)
            }
        }

        binding.btnSave.setOnClickListener {
            val password = binding.txtNewPasswordField.text.toString().trim()
            val confirmPassword = binding.txtConfirmPasswordField.text.toString().trim()
            val currentPassword = binding.txtCurrentPasswordField.text.toString().trim()
            if (password.isEmpty()) {
                binding.newPasswordInputLayout.apply {
                    isErrorEnabled = true
                    error = "This field is required"
                    requestFocus()
                }
                (getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager).showSoftInput(binding.txtNewPasswordField, InputMethodManager.SHOW_IMPLICIT)
            }
            if (confirmPassword.isEmpty()) {
                binding.confirmPasswordInputLayout.apply {
                    isErrorEnabled = true
                    error = "This field is required"
                }
            } else if (password != confirmPassword) {
                binding.confirmPasswordInputLayout.apply {
                    isErrorEnabled = true
                    error = "Passwords not matched"
                }
            }
            if (currentPassword.isEmpty()) {
                binding.currentPasswordInputLayout.apply {
                    isErrorEnabled = true
                    error = "This field is required"
                }
            }
        }

        binding.txtNewPasswordField.doOnTextChanged { _, _, _, _ ->
            binding.newPasswordInputLayout.apply {
                error = null
                isErrorEnabled = false
            }
        }

        binding.txtConfirmPasswordField.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.confirmPasswordInputLayout.apply {
                    error = null
                    isErrorEnabled = false
                }
            }
        }

        binding.txtCurrentPasswordField.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.currentPasswordInputLayout.apply {
                    error = null
                    isErrorEnabled = false
                }
            }
        }
    }
}