package com.example.kotlin_demoapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.databinding.RecyclerItemBinding

class SearchRecyclerAdapter(private var cityList: Array<String>) :
    RecyclerView.Adapter<SearchRecyclerAdapter.SearchRecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchRecyclerViewHolder =
        SearchRecyclerViewHolder(
            RecyclerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: SearchRecyclerViewHolder, position: Int) {
        holder.bind(cityList[position])
    }

    override fun getItemCount(): Int {
        return cityList.count()
    }

    fun setFilteredData(filteredList: Array<String>) {
        cityList = filteredList
        notifyDataSetChanged()
    }

    class SearchRecyclerViewHolder(val binding: RecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(title: String) {
            binding.txtRecycleTitle.text = title
        }
    }
}