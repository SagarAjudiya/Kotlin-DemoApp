package com.example.kotlin_demoapp.screens.go_tour

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Toast
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.custom_views.CustomAlert
import com.example.kotlin_demoapp.databinding.ActivityGoTourSignUpBinding
import com.google.android.material.snackbar.Snackbar

class GoTourSignUp : AppCompatActivity() {

    private lateinit var binding: ActivityGoTourSignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGoTourSignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val spannableString = SpannableString(binding.txtTermCondition.text.toString())
        val termString = "term to use"
        val startIndex = binding.txtTermCondition.text.toString().indexOf(termString, 0)
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                Toast.makeText(applicationContext, "Term and Conditions", Toast.LENGTH_LONG).show()
                view.invalidate()
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.parseColor("#ff7d0d")
                ds.isUnderlineText = false
                binding.txtTermCondition.invalidate()
            }
        }
        spannableString.setSpan(
            clickableSpan, startIndex, startIndex + termString.length,
            Spanned.SPAN_INCLUSIVE_INCLUSIVE
        )
        binding.txtTermCondition.highlightColor = resources.getColor(R.color.transparent)
        binding.txtTermCondition.movementMethod = LinkMovementMethod.getInstance()
        binding.txtTermCondition.text = spannableString

        binding.btnSignUp.setOnClickListener {
            CustomAlert(this, "Confirm Sign Up", "Confirm all details you have entered.") {
                Snackbar.make(binding.root, "Sign Up Successfully.", Snackbar.LENGTH_SHORT).show()
                Handler(Looper.getMainLooper()).postDelayed({
                    startActivity(
                        Intent(
                            this,
                            GoTourUpcoming::class.java
                        )
                    )
                }, 1000)

            }.show()
        }
    }
}