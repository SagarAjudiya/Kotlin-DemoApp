package com.example.kotlin_demoapp.activity

import android.graphics.Rect
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_demoapp.Helper
import com.example.kotlin_demoapp.adapters.YouTubeMainRecyclerAdapter
import com.example.kotlin_demoapp.databinding.ActivityYoutubeBinding

class YouTubeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityYoutubeBinding
    private lateinit var adapter: YouTubeMainRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityYoutubeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = YouTubeMainRecyclerAdapter()
        adapter.submitList(Helper.getYouTubeData(this))
        binding.mainRecycler.layoutManager = LinearLayoutManager(this)
        binding.mainRecycler.adapter = adapter

        binding.mainRecycler.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                outRect.bottom = 20
            }
        })
    }
}