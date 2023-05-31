package com.example.kotlin_demoapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_demoapp.R

class SearchRecyclerAdapter(private var cityList: ArrayList<String>): RecyclerView.Adapter<SearchRecyclerAdapter.SearchRecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item,parent,false)
        return SearchRecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchRecyclerViewHolder, position: Int) {
        holder.title.text = cityList[position]
    }

    override fun getItemCount(): Int {
        return cityList.count()
    }

    fun setFilteredData(filteredList: ArrayList<String>){
        cityList = filteredList
        notifyDataSetChanged()
    }

    class SearchRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.txtRecycleTitle)
    }
}