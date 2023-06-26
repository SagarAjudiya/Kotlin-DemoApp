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
import com.example.kotlin_demoapp.databinding.RecyclerItemBinding

class GridRecyclerAdapter(private val recycleData: ArrayList<String>) :
    RecyclerView.Adapter<GridRecyclerAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {

        return RecyclerViewHolder(
            RecyclerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(recycleData[position])
    }

    override fun getItemCount(): Int {
        return recycleData.count()
    }

    class RecyclerViewHolder(val binding: RecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(title: String) {
            binding.txtRecycleTitle.text = title

            binding.cardImage.updateLayoutParams<ConstraintLayout.LayoutParams> {
                bottomToBottom = ConstraintLayout.LayoutParams.UNSET
                endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
            }

            binding.txtRecycleTitle.updateLayoutParams<ConstraintLayout.LayoutParams> {
                startToEnd = ConstraintLayout.LayoutParams.UNSET
                topToTop = ConstraintLayout.LayoutParams.UNSET
                bottomToBottom = ConstraintLayout.LayoutParams.UNSET

                topToBottom = binding.cardImage.id
                topMargin = 10
                marginStart = 0
                startToStart = binding.cardImage.id
                endToEnd = binding.cardImage.id
                bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
            }
        }
    }
}