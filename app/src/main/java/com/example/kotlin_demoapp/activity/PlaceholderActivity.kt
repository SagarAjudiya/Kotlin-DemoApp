package com.example.kotlin_demoapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.TransitionManager
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Placeholder
import com.example.kotlin_demoapp.databinding.ActivityPlaceholderBinding

class PlaceholderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlaceholderBinding
    private lateinit var placeholder: Placeholder
    private lateinit var layout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPlaceholderBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        placeholder = binding.placeholder
        layout = binding.layout
    }

    fun selectImage(view: View) {
        TransitionManager.beginDelayedTransition(layout)
        placeholder.setContentId(view.id)
    }
}