package com.example.kotlin_demoapp.activity.intent

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin_demoapp.databinding.ActivityResultBinding
import com.example.kotlin_demoapp.model.PersonDetails

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener {
            finish()
        }

        binding.apply {
            txtTitle.text = intent.extras?.getString("Title")
            val person: PersonDetails? = intent.getParcelableExtra("person")
            person?.let { safePerson -> binding.person = safePerson }
        }
    }

    override fun finish() {
        setResult(RESULT_OK, Intent().apply {
            putExtra(
                "person", PersonDetails(
                    binding.etName.text.toString(),
                    binding.etSurname.text.toString(),
                    binding.etEmail.text.toString(),
                    binding.etPhone.text.toString()
                )
            )
        })
        super.finish()
    }
}