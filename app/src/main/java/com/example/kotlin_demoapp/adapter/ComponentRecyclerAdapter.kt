package com.example.kotlin_demoapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.enums.Components

class ComponentRecyclerAdapter(private val context: Context, private val componentList: Array<Components>) :
    RecyclerView.Adapter<ComponentRecyclerAdapter.ComponentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComponentViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.component_recycler_item, parent, false)
        return ComponentViewHolder(view)
    }

    override fun onBindViewHolder(holder: ComponentViewHolder, position: Int) {
        holder.button?.text = componentList[position].name
        holder.button?.setOnClickListener {
            context.startActivity(Intent(context, componentList[position].activity))
        }
    }

    override fun getItemCount(): Int {
        return componentList.count()
    }

    class ComponentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val button = itemView.findViewById<Button>(R.id.componentButton) ?: null
    }
}