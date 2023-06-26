package com.example.kotlin_demoapp.screens.go_tour

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin_demoapp.databinding.ActivityGoTourLoginBinding

class GoTourLogin : AppCompatActivity() {

    private lateinit var binding: ActivityGoTourLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGoTourLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignUp.setOnClickListener {
            startActivity(Intent(this@GoTourLogin, GoTourSignUp::class.java))
        }

        binding.btnForgotPass.setOnClickListener {
            Toast.makeText(this, "Forgot Password", Toast.LENGTH_SHORT).show()
        }

        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this@GoTourLogin, GoTourUpcoming::class.java))
        }

        binding.btnGoogleLogin.setOnClickListener {
            startActivity(Intent(this@GoTourLogin, GoTourDiscover::class.java))
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val inputMethodManager =
                this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)
            (currentFocus as? EditText)?.clearFocus()
        }
        return super.dispatchTouchEvent(ev)
    }
}