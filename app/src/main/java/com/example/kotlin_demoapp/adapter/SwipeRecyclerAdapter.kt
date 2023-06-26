package com.example.kotlin_demoapp.adapter

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.databinding.RecyclerItemBinding

class SwipeRecyclerAdapter(val cityList: ArrayList<String>) :
    RecyclerView.Adapter<SwipeRecyclerAdapter.SwipeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SwipeViewHolder =
        SwipeViewHolder(
            RecyclerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: SwipeViewHolder, position: Int) {
        holder.bind(cityList[position])
    }

    override fun getItemCount(): Int {
        return cityList.count()
    }

    class SwipeViewHolder(val binding: RecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(title: String) {
            binding.txtRecycleTitle.text = title
        }
    }
}