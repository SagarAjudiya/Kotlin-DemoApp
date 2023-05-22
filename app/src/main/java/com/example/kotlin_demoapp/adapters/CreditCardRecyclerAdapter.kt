package com.example.kotlin_demoapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_demoapp.databinding.CardCreditCardBinding
import com.example.kotlin_demoapp.model.CreditCardData

class CreditCardRecyclerAdapter :
    RecyclerView.Adapter<CreditCardRecyclerAdapter.CreditCardViewHolder>() {

    private val creditCardList = ArrayList<CreditCardData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreditCardViewHolder =
        CreditCardViewHolder(
            CardCreditCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CreditCardViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return creditCardList.count()
    }

    inner class CreditCardViewHolder(private val binding: CardCreditCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            binding.creditCard = creditCardList[position]
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newList: MutableList<CreditCardData>) {
        creditCardList.clear()
        creditCardList.addAll(newList)
        notifyDataSetChanged()
    }
}