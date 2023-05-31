package com.example.kotlin_demoapp.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_demoapp.databinding.ComponentRecyclerItemBinding
import com.example.kotlin_demoapp.enums.IntentTypes

class IntentRecyclerAdapter : RecyclerView.Adapter<IntentRecyclerAdapter.IntentViewHolder>() {

    private var intentList = ArrayList<IntentTypes>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntentViewHolder {
        return IntentViewHolder(
            ComponentRecyclerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: IntentViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return intentList.count()
    }

    inner class IntentViewHolder(private val binding: ComponentRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            binding.componentButton.apply {
                text = intentList[position].name
                setOnClickListener {
                    context.startActivity(Intent(context, intentList[position].activity))
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newList: Array<IntentTypes>) {
        intentList.clear()
        intentList.addAll(newList)
        notifyDataSetChanged()
    }
}