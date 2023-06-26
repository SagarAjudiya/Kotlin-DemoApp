package com.example.kotlin_demoapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.databinding.ViewPagerItemBinding
import com.example.kotlin_demoapp.model.CityDetails

class ViewPagerRecyclerAdapter(private val cityList: MutableList<CityDetails>) :
    RecyclerView.Adapter<ViewPagerRecyclerAdapter.ViewPagerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder =
        ViewPagerViewHolder(
            ViewPagerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        holder.bind(cityList[position])
    }

    override fun getItemCount(): Int {
        return cityList.count()
    }

    class ViewPagerViewHolder(val binding: ViewPagerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(city: CityDetails) {
            binding.apply {
                cityName.text = city.name
                cityDescription.text = city.description
                cityImage.setImageResource(city.image)
            }
        }
    }
}
