package com.example.kotlin_demoapp.tagb.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.databinding.ItemMovieBinding
import com.example.kotlin_demoapp.tagb.helper.Constants
import com.example.kotlin_demoapp.tagb.module.dashboard.model.response.MovieInfo

class MovieRecyclerAdapter :
    ListAdapter<MovieInfo, MovieRecyclerAdapter.MovieViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MovieViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MovieInfo) {
            binding.movie = item
            Glide.with(binding.root).load(Constants.MOVIE_IMAGE_BASE + item.image)
                .placeholder(R.drawable.img_not_found).into(binding.imgMovie)
        }
    }

    class MovieDiffCallback : DiffUtil.ItemCallback<MovieInfo>() {
        override fun areItemsTheSame(oldItem: MovieInfo, newItem: MovieInfo): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: MovieInfo, newItem: MovieInfo): Boolean {
            return oldItem == newItem
        }
    }
}