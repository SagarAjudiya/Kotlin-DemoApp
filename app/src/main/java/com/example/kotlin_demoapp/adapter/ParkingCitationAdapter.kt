package com.example.kotlin_demoapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_demoapp.databinding.PaidParkingCitationItemBinding
import com.example.kotlin_demoapp.databinding.UnpaidParkingCitationItemBinding
import com.example.kotlin_demoapp.model.ParkingModel

class ParkingCitationAdapter(private val selectParkingTicket: (Int, Boolean) -> Unit, private val changeStatus: (Int) -> Unit) :
    ListAdapter<ParkingModel,RecyclerView.ViewHolder>(ParkingItemDiffCallback()) {

    enum class ParkingStatus {
        PAID,
        UNPAID
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position).status) {
        true -> ParkingStatus.PAID.ordinal
        false -> ParkingStatus.UNPAID.ordinal
    }

    inner class PaidViewHolder(private val binding: PaidParkingCitationItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(parkingModel: ParkingModel, position: Int) {
            binding.parkingData = parkingModel
            binding.btnDetails.setOnClickListener {
                changeStatus(position)
            }
            binding.checkbox.setOnCheckedChangeListener { _, isChecked ->
                selectParkingTicket(position, isChecked)
            }
        }
    }

    inner class UnpaidViewHolder(private val binding: UnpaidParkingCitationItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(parkingModel: ParkingModel, position: Int) {
            binding.parkingData = parkingModel
            binding.btnPay.setOnClickListener {
                changeStatus(position)
            }
            binding.checkbox.setOnCheckedChangeListener { _, isChecked ->
                selectParkingTicket(position, isChecked)
            }
        }
    }

    class ParkingItemDiffCallback: DiffUtil.ItemCallback<ParkingModel>() {
        override fun areItemsTheSame(oldItem: ParkingModel, newItem: ParkingModel): Boolean {
            return oldItem.citationID == newItem.citationID
        }

        override fun areContentsTheSame(oldItem: ParkingModel, newItem: ParkingModel): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (ParkingStatus.values()[viewType]) {
            ParkingStatus.PAID -> PaidViewHolder(
                PaidParkingCitationItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            ParkingStatus.UNPAID -> UnpaidViewHolder(
                UnpaidParkingCitationItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is PaidViewHolder -> holder.bind(getItem(position), position)
            is UnpaidViewHolder -> holder.bind(getItem(position), position)
        }
    }
}
