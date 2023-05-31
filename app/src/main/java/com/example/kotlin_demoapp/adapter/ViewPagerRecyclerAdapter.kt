package com.example.kotlin_demoapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.model.CityDetails

class ViewPagerRecyclerAdapter(val cityList: MutableList<CityDetails>): RecyclerView.Adapter<ViewPagerRecyclerAdapter.ViewPagerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_pager_item,parent,false)
        return ViewPagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        holder.cityImage.setImageResource(cityList[position].image)
        holder.cityName.text = cityList[position].name
        holder.cityDescription.text = cityList[position].description
    }

    override fun getItemCount(): Int {
        return cityList.count()
    }

    class ViewPagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cityImage = itemView.findViewById<ImageView>(R.id.cityImage)
        val cityName = itemView.findViewById<TextView>(R.id.cityName)
        val cityDescription = itemView.findViewById<TextView>(R.id.cityDescription)
    }
}