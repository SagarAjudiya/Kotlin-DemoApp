package com.example.kotlin_demoapp.adapters

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.example.kotlin_demoapp.databinding.YoutubeCommentItemBinding
import com.example.kotlin_demoapp.databinding.YoutubeNormalItemBinding
import com.example.kotlin_demoapp.databinding.YoutubeSnapImagesItemBinding
import com.example.kotlin_demoapp.enums.YouTubeItemType
import com.example.kotlin_demoapp.model.CommentModel
import com.example.kotlin_demoapp.model.NormalModel
import com.example.kotlin_demoapp.model.SnapImagesModel
import com.example.kotlin_demoapp.model.YouTubeModel

class YouTubeMainRecyclerAdapter :
    ListAdapter<YouTubeModel, RecyclerView.ViewHolder>(YouTubeItemDiffCallBack()) {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        context = parent.context

        return when (YouTubeItemType.values()[viewType]) {
            YouTubeItemType.NORMAL -> NormalViewHolder(
                YoutubeNormalItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            YouTubeItemType.COMMENT -> CommentViewHolder(
                YoutubeCommentItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            YouTubeItemType.SNAP_IMAGES -> SnapImagesViewHolder(
                YoutubeSnapImagesItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is NormalViewHolder -> holder.bind(position)
            is CommentViewHolder -> holder.bind(position)
            is SnapImagesViewHolder -> holder.bind(position)
        }
    }

    override fun getItemViewType(position: Int): Int = getItem(position).type.ordinal

    inner class NormalViewHolder(private val binding: YoutubeNormalItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            binding.normalData = getItem(position).item as NormalModel
            binding.txtImageCount.visibility = View.GONE
        }
    }

    inner class CommentViewHolder(private val binding: YoutubeCommentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            binding.commentData = getItem(position).item as CommentModel
            binding.commentCard.setOnClickListener {
                when (binding.commentDescription.visibility) {
                    View.GONE -> binding.commentDescription.visibility = View.VISIBLE
                    View.VISIBLE -> binding.commentDescription.visibility = View.GONE
                    else -> {}
                }
            }
        }
    }

    inner class SnapImagesViewHolder(private val binding: YoutubeSnapImagesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        private val linearSnapHelper = LinearSnapHelper()
        private val snapImageAdapter = SnapImageRecyclerAdapter()

        init {
            binding.snapImagesRecycler.addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    outRect.right = 20
                    outRect.left = 20
                }
            })
        }

        fun bind(position: Int) {
            val snapImageModel = getItem(position).item as SnapImagesModel
            snapImageAdapter.submitList(snapImageModel.imageList)
            binding.snapImagesRecycler.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            binding.snapImagesRecycler.adapter = snapImageAdapter

            linearSnapHelper.attachToRecyclerView(binding.snapImagesRecycler)
        }
    }

    class YouTubeItemDiffCallBack : DiffUtil.ItemCallback<YouTubeModel>() {
        override fun areItemsTheSame(oldItem: YouTubeModel, newItem: YouTubeModel): Boolean {
            return oldItem.type == newItem.type
        }

        override fun areContentsTheSame(oldItem: YouTubeModel, newItem: YouTubeModel): Boolean {
            return oldItem == newItem
        }
    }
}