package com.example.kotlin_demoapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_demoapp.databinding.RecyclerItemBinding
import com.example.kotlin_demoapp.model.CityDescription

class ExpendableRecyclerAdapter(private val cityList: ArrayList<CityDescription>) :
    RecyclerView.Adapter<ExpendableRecyclerAdapter.ExpendableRecyclerViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExpendableRecyclerViewHolder {

        return ExpendableRecyclerViewHolder(
            RecyclerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ExpendableRecyclerViewHolder, position: Int) {
        holder.bind(cityList[position])
    }

    override fun getItemCount(): Int {
        return cityList.count()
    }

    class ExpendableRecyclerViewHolder(val binding: RecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.recyclerItemCard.setOnClickListener {
                when (binding.txtRecyclerDescription.visibility) {
                    View.VISIBLE -> binding.txtRecyclerDescription.visibility = View.GONE
                    View.GONE -> binding.txtRecyclerDescription.visibility = View.VISIBLE
                    else -> binding.txtRecyclerDescription.visibility = View.VISIBLE
                }
            }
        }

        fun bind(city: CityDescription) {
            binding.txtRecycleTitle.text = city.name
            binding.txtRecyclerDescription.text = city.description
        }
    }
}