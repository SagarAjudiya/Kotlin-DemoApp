package com.example.kotlin_demoapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.model.CityDescription

class ExpendableRecyclerAdapter(val cityList: ArrayList<CityDescription>): RecyclerView.Adapter<ExpendableRecyclerAdapter.ExpendableRecyclerViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExpendableRecyclerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item,parent,false)
        val description = itemView.findViewById<TextView>(R.id.txtRecyclerDescription)
        val itemCard = itemView.findViewById<CardView>(R.id.recyclerItemCard)
        itemCard.setOnClickListener {
            when(description.visibility){
                View.VISIBLE -> description.visibility = View.GONE
                View.GONE -> description.visibility = View.VISIBLE
                else -> description.visibility = View.VISIBLE
            }
        }
        return ExpendableRecyclerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExpendableRecyclerViewHolder, position: Int) {
        holder.title.text = cityList[position].name
        holder.description.text = cityList[position].desription
    }

    override fun getItemCount(): Int {
        return cityList.count()
    }

    class ExpendableRecyclerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.txtRecycleTitle)
        val description = itemView.findViewById<TextView>(R.id.txtRecyclerDescription)
    }
}