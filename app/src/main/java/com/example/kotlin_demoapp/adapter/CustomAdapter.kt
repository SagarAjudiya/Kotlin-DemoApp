package com.example.kotlin_demoapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.databinding.CustomSpinnerItemsBinding


class CustomAdapter(var context: Context, var flags: IntArray, var countryNames: Array<String>) : BaseAdapter() {
    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getItem(position: Int): Any {
        return 0
    }

    override fun getCount(): Int {
        return flags.count()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding = CustomSpinnerItemsBinding.inflate(LayoutInflater.from(context), parent, false)
        binding.spinnerImage.setImageResource(flags[position])
        binding.spinnerText.text = countryNames[position]
        return binding.root
    }
}