package com.example.kotlin_demoapp.tagb.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.databinding.ItemEmployeeBinding
import com.example.kotlin_demoapp.tagb.helper.utcToLocal
import com.example.kotlin_demoapp.tagb.module.dashboard.model.response.EmployeeInfo

class EmployeeRecyclerAdapter(
    private var editEmployee: (EmployeeInfo, Int) -> Unit,
    private var deleteEmployee: (String, Int) -> Unit,
    private var showEmployee: (String) -> Unit
) : ListAdapter<EmployeeInfo, EmployeeRecyclerAdapter.EmployeeViewHolder>(
    EmployeeDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder =
        EmployeeViewHolder(
            ItemEmployeeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class EmployeeViewHolder(val binding: ItemEmployeeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.btnEdit.setOnClickListener {
                editEmployee(getItem(adapterPosition), adapterPosition)
            }

            binding.btnDelete.setOnClickListener {
                deleteEmployee(getItem(adapterPosition).id, adapterPosition)
            }

            binding.itemEmployee.setOnClickListener {
                showEmployee(getItem(adapterPosition).id)
            }
        }

        fun bind(item: EmployeeInfo) {
            binding.employee = item
            binding.txtCreated.text = utcToLocal(item.createdDate)
            Glide.with(binding.root).load(item.image).placeholder(R.drawable.img_not_found)
                .error(R.drawable.img_not_found).into(binding.imgEmployee)
        }
    }

    override fun submitList(list: MutableList<EmployeeInfo>?) {
        super.submitList(list?.let { ArrayList(it) })
    }

    class EmployeeDiffCallback : DiffUtil.ItemCallback<EmployeeInfo>() {
        override fun areItemsTheSame(oldItem: EmployeeInfo, newItem: EmployeeInfo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: EmployeeInfo, newItem: EmployeeInfo): Boolean {
            return oldItem == newItem
        }
    }
}