package com.example.kotlin_demoapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_demoapp.R

class StaggeredRecyclerAdapter(private val recycleData: ArrayList<String>) :
    RecyclerView.Adapter<StaggeredRecyclerAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        val imgCard = itemView.findViewById<CardView>(R.id.cardImage)
        val txtTitle = itemView.findViewById<TextView>(R.id.txtRecycleTitle)

        imgCard.updateLayoutParams<ConstraintLayout.LayoutParams> {
            bottomToBottom = ConstraintLayout.LayoutParams.UNSET
            endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
        }

        txtTitle.updateLayoutParams<ConstraintLayout.LayoutParams> {
            startToEnd = ConstraintLayout.LayoutParams.UNSET
            topToTop = ConstraintLayout.LayoutParams.UNSET
            bottomToBottom = ConstraintLayout.LayoutParams.UNSET

            topToBottom = imgCard.id
            topMargin = 10
            marginStart = 0
            startToStart = imgCard.id
            endToEnd = imgCard.id
            bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
        }

        return RecyclerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.title.text = recycleData[position]

    }

    override fun getItemCount(): Int {
        return recycleData.count()
    }

    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.txtRecycleTitle)
    }
}