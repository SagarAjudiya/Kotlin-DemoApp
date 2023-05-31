package com.example.kotlin_demoapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_demoapp.R

class SwipeRecyclerAdapter(val cityList: ArrayList<String>): RecyclerView.Adapter<SwipeRecyclerAdapter.SwipeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SwipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item,parent,false)
        return SwipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: SwipeViewHolder, position: Int) {
        holder.cityName.text = cityList[position]
    }

    override fun getItemCount(): Int {
        return cityList.count()
    }

    class SwipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cityName = itemView.findViewById<TextView>(R.id.txtRecycleTitle)
    }
}