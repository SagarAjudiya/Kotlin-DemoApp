package com.example.kotlin_demoapp.adapters

import android.graphics.drawable.Drawable
import android.util.Pair
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_demoapp.databinding.YoutubeNormalItemBinding
import com.example.kotlin_demoapp.model.NormalModel

class SnapImageRecyclerAdapter :
    RecyclerView.Adapter<SnapImageRecyclerAdapter.SnapImageViewHolder>() {

    private var snapImageList = ArrayList<Drawable>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SnapImageViewHolder {
        return SnapImageViewHolder(
            YoutubeNormalItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SnapImageViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return snapImageList.count()
    }

    inner class SnapImageViewHolder(private val binding: YoutubeNormalItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            binding.normalData = NormalModel(snapImageList[position])
            binding.countPair = Pair(position + 1, snapImageList.count())
        }
    }

    fun submitList(newList: ArrayList<Drawable>) {
        snapImageList = newList
        notifyDataSetChanged()
    }

}