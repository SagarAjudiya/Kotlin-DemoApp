package com.example.kotlin_demoapp.activity.components

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.databinding.ActivityCalenderBinding
import com.example.kotlin_demoapp.helper.Helper
import java.util.Calendar


class Calender : AppCompatActivity() {

    private lateinit var binding: ActivityCalenderBinding
    private val months = Helper.getMonth()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCalenderBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.calendar.setOnDateChangeListener { _, year, month, dayOfMonth ->
            binding.txtCalender.text = String.format(
                getString(R.string.local_date),
                dayOfMonth.toString(),
                (month + 1).toString(),
                year.toString()
            )
        }

        binding.txtCalenderField.setOnClickListener {
            DatePickerDialog(
                this,
                { _, year, monthOfYear, dayOfMonth ->
//                    binding.txtCalenderField.setText("" + dayOfMonth + " " + months[monthOfYear] + ", " + year)
                    binding.txtCalenderField.setText(String.format(
                        getString(R.string.date_with_month),
                        dayOfMonth.toString(),
                        months[monthOfYear],
                        year.toString()
                    ))
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
//                    binding.txtTimeField.setText("" + hour + ":" + minute)
                    binding.txtTimeField.setText(String.format(
                        getString(R.string.time),
                        hour.toString(),
                        minute.toString()
                    ))
                },
                Calendar.getInstance().get(Calendar.HOUR),
                Calendar.getInstance().get(Calendar.MINUTE),
                false
            ).show()
        }

        binding.materialPickers.setOnClickListener {
            startActivity(Intent(this, MaterialCalender::class.java))
        }
    }
}