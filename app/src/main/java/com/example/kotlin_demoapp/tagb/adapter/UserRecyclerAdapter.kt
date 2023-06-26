package com.example.kotlin_demoapp.tagb.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlin_demoapp.databinding.ItemUserBinding
import com.example.kotlin_demoapp.tagb.module.dashboard.model.response.PersonAPI

class UserRecyclerAdapter : RecyclerView.Adapter<UserRecyclerAdapter.UserViewHolder>() {

    private var userList: MutableList<PersonAPI> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount(): Int {
        return userList.count()
    }

    class UserViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(personAPI: PersonAPI) {
            binding.person = personAPI
            Glide.with(binding.root).load(personAPI.image).into(binding.image)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: MutableList<PersonAPI>) {
        this.userList = list
        notifyDataSetChanged()
    }
}