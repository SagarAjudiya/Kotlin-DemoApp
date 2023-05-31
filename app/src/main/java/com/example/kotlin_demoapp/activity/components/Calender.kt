package com.example.kotlin_demoapp.activity.components

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin_demoapp.databinding.ActivityCalenderBinding
import java.util.Calendar


class Calender : AppCompatActivity() {

    private lateinit var binding: ActivityCalenderBinding
    private val months = arrayListOf<String>(
        "Jan",
        "Feb",
        "Mar",
        "Apr",
        "May",
        "June",
        "July",
        "Aug",
        "Sep",
        "Oct",
        "Nov",
        "Dec"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCalenderBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.calendar.setOnDateChangeListener { _, year, month, dayOfMonth ->
            binding.txtCalender.text = "$dayOfMonth / ${month + 1} / $year"
        }

        binding.txtCalenderField.setOnClickListener {
            DatePickerDialog(
                this,
                { _, year, monthOfYear, dayOfMonth ->
                    binding.txtCalenderField.setText("" + dayOfMonth + " " + months[monthOfYear] + ", " + year)
                },
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        binding.txtTimeField.setOnClickListener {
            TimePickerDialog(
                this,
                { _, hour, minute ->
                    binding.txtTimeField.setText("" + hour + ":" + minute)
                },
                Calendar.getInstance().get(Calendar.HOUR),
                Calendar.getInstance().get(Calendar.MINUTE),
                false
            ).show()
        }

        binding.materialPickers.setOnClickListener {
            startActivity(Intent(this,
                com.example.kotlin_demoapp.activity.components.MaterialCalender::class.java))
        }
    }
}