package com.example.kotlin_demoapp.activity.components

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.core.text.HtmlCompat
import androidx.core.widget.addTextChangedListener
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.databinding.ActivityEdittextBinding
import com.example.kotlin_demoapp.databinding.ActivityTextviewBinding
import java.util.regex.Pattern

class TextView : AppCompatActivity() {

    private lateinit var binding: ActivityTextviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTextviewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        // set underline
        binding.underlineText.text = HtmlCompat.fromHtml("<u>"+binding.underlineText.text.toString()+"</u>", HtmlCompat.FROM_HTML_MODE_LEGACY)
        // set different colored text
        binding.differentColorText.text = HtmlCompat.fromHtml("This <font color='#cc0952'>text</font> is <font color='#339900'>different</font> <font color='#1034a6'>colored</font> text", HtmlCompat.FROM_HTML_MODE_LEGACY)
        // clickable text
        binding.clickText.setOnClickListener {
            Toast.makeText(this, "Text was clicked", Toast.LENGTH_SHORT).show()
        }
        // long pressed text
        binding.longPressText.setOnLongClickListener {
            Toast.makeText(this, "Long Pressed", Toast.LENGTH_SHORT).show()
            binding.longPressText.text = HtmlCompat.fromHtml("<b>"+binding.longPressText.text.toString()+"</b>", HtmlCompat.FROM_HTML_MODE_LEGACY)
            return@setOnLongClickListener true
        }

    }
}