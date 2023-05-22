package com.example.kotlin_demoapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_demoapp.databinding.CardAddUserBinding
import com.example.kotlin_demoapp.databinding.CardUsersBinding
import com.example.kotlin_demoapp.model.UserData

class UserRecyclerAdapter(private val addUser: () -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val userList = ArrayList<UserData>()

    enum class UserType {
        CREATE_USER,
        USER
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (UserType.values()[viewType]) {
            UserType.CREATE_USER -> CreateUserViewHolder(
                CardAddUserBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            UserType.USER -> UserViewHolder(
                CardUsersBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

    override fun getItemViewType(position: Int): Int = when (position) {
        0 -> UserType.CREATE_USER.ordinal
        else -> UserType.USER.ordinal
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CreateUserViewHolder -> holder.bind(position)
            is UserViewHolder -> holder.bind(position)
        }
    }

    override fun getItemCount(): Int {
        return userList.count() + 1
    }

    inner class UserViewHolder(private val binding: CardUsersBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            binding.user = userList[position - 1]
        }
    }

    inner class CreateUserViewHolder(private val binding: CardAddUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            binding.selectUser.setOnClickListener {
                addUser()
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newList: MutableList<UserData>) {
        userList.clear()
        userList.addAll(newList)
        notifyDataSetChanged()
    }

}