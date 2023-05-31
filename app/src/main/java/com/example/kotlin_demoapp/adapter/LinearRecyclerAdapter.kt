package com.example.kotlin_demoapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.helper.LinearRecyclerDiffUtil

class LinearRecyclerAdapter: RecyclerView.Adapter<LinearRecyclerAdapter.RecyclerViewHolder>() {

    private val recycleData: ArrayList<String> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item,parent,false)
        return RecyclerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.title.text = recycleData[position]
    }

    override fun getItemCount(): Int {
        return recycleData.count()
    }

    fun setData(newData: ArrayList<String>) {
        val diffUtil = LinearRecyclerDiffUtil(recycleData, newData)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        recycleData.clear()
        recycleData.addAll(newData)
        diffResult.dispatchUpdatesTo(this)
    }

    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.txtRecycleTitle)
    }
}