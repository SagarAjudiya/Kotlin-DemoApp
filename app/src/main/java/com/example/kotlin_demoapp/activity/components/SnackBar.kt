package com.example.kotlin_demoapp.activity.components

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.databinding.ActivitySnackBarBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.SnackbarLayout

class SnackBar : AppCompatActivity() {

    private lateinit var binding: ActivitySnackBarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySnackBarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.normalSnackBar.setOnClickListener {
            Snackbar.make(it, "Normal SnackBar", Snackbar.LENGTH_LONG).show()
        }

        binding.actionSnackBar.setOnClickListener {
            Snackbar.make(it, "Action SnackBar", Snackbar.LENGTH_INDEFINITE).apply {
                setAction("Dismiss", View.OnClickListener {
                    Toast.makeText(this@SnackBar, "SnackBar Dismissed", Toast.LENGTH_SHORT).show()
                })
                setActionTextColor(getColor(R.color.black))
                setBackgroundTint(getColor(R.color.light_orange))
                setTextColor(getColor(R.color.orange))
                animationMode = Snackbar.ANIMATION_MODE_SLIDE
                show()
            }
        }

        binding.customSnackBar.setOnClickListener {
            val view = layoutInflater.inflate(R.layout.custom_snackbar, null)
            val snackBar = Snackbar.make(it, "", Snackbar.LENGTH_INDEFINITE)
            val snackBarLayout = snackBar.view as SnackbarLayout

            snackBarLayout.setPadding(0, 0, 0, 0)

            view.findViewById<Button>(R.id.btnDismiss).setOnClickListener {
                Toast.makeText(this, "Dismissed", Toast.LENGTH_SHORT).show()
                snackBar.dismiss()
            }
            snackBarLayout.addView(view)
            snackBar.show()
        }
    }
}