package com.example.kotlin_demoapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_demoapp.databinding.RecyclerItemBinding
import com.example.kotlin_demoapp.model.ImageHolder

class ImagePickerRecyclerAdapter: ListAdapter<ImageHolder,ImagePickerRecyclerAdapter.ImagePickerViewHolder>(ImagePickerItemDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagePickerViewHolder {
        return ImagePickerViewHolder(
            RecyclerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ImagePickerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ImagePickerViewHolder(private val binding: RecyclerItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(imageHolder: ImageHolder) {
            binding.txtRecycleTitle.visibility = View.GONE
            binding.bottomDivider.visibility = View.GONE
            binding.imgRecycler.setImageURI(imageHolder.imageURI)
            binding.cardImage.updateLayoutParams<ConstraintLayout.LayoutParams> {
                endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
            }
        }
    }

    class ImagePickerItemDiffCallBack: DiffUtil.ItemCallback<ImageHolder>() {
        override fun areItemsTheSame(oldItem: ImageHolder, newItem: ImageHolder): Boolean {
            return oldItem.imageURI == newItem.imageURI
        }

        override fun areContentsTheSame(oldItem: ImageHolder, newItem: ImageHolder): Boolean {
            return oldItem == newItem
        }
    }
}
