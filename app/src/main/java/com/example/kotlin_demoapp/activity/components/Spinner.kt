package com.example.kotlin_demoapp.activity.components

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.adapter.CustomAdapter
import com.example.kotlin_demoapp.databinding.ActivitySpinnerBinding
import com.example.kotlin_demoapp.helper.Helper


class Spinner : AppCompatActivity() {

    lateinit var binding: ActivitySpinnerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySpinnerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val names = mutableListOf<String>("Default")
        val adapter = ArrayAdapter<String>(
            this,
            com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
            names
        )
        adapter.setDropDownViewResource(com.google.android.material.R.layout.support_simple_spinner_dropdown_item)
        binding.spinner.adapter = adapter

        binding.btnAdd.setOnClickListener {
            val text = binding.txtName.text.toString()
            if (text.isNotEmpty()) {
                names.add(text)
                adapter.notifyDataSetChanged()
                binding.txtName.text = null
            }
        }

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val text = parent?.getItemAtPosition(position).toString()
                binding.txtName.text = SpannableStringBuilder(text)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        val countryNames = Helper.getCountryName()
        val flags = intArrayOf(
            R.drawable.icon_android,
            R.drawable.icon_calender,
            R.drawable.icon_cancel,
            R.drawable.icon_copy,
            R.drawable.icon_edit,
            R.drawable.icon_info
        )

        val customAdapter = CustomAdapter(this,flags,countryNames)
        binding.customSpinner.adapter = customAdapter

        binding.customSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(this@Spinner, countryNames[position], Toast.LENGTH_LONG).show();
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }
}