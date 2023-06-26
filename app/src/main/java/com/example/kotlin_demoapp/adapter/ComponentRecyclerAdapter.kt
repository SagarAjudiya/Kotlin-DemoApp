package com.example.kotlin_demoapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.databinding.ComponentRecyclerItemBinding
import com.example.kotlin_demoapp.enums.Components

class ComponentRecyclerAdapter(private val context: Context, private val componentList: Array<Components>) :
    RecyclerView.Adapter<ComponentRecyclerAdapter.ComponentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComponentViewHolder = ComponentViewHolder(
        parent.context,
        ComponentRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ComponentViewHolder, position: Int) {
        holder.bind(componentList[position].name)
    }

    override fun getItemCount(): Int {
        return componentList.count()
    }

    inner class ComponentViewHolder(val context: Context, val binding: ComponentRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.componentButton.setOnClickListener {
                context.startActivity(Intent(context, componentList[adapterPosition].activity))
            }
        }
        fun bind(btnName: String) {
            binding.componentButton.text = btnName
        }
    }
}