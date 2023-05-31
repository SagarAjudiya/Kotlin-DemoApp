package com.example.kotlin_demoapp.screens.go_tour

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.kotlin_demoapp.databinding.ActivityGoTourLoginBinding

class GoTourLogin : AppCompatActivity() {

    private lateinit var binding: ActivityGoTourLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGoTourLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

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
}