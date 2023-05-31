package com.example.kotlin_demoapp.activity.components

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.kotlin_demoapp.R


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

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.custom_spinner_items, null)
        view.findViewById<ImageView>(R.id.spinnerImage).setImageResource(flags[position])
        view.findViewById<TextView>(R.id.spinnerText).text = countryNames[position]
        return view
    }
}