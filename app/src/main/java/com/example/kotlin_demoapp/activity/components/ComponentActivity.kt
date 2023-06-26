package com.example.kotlin_demoapp.activity.components

import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.adapter.ComponentRecyclerAdapter
import com.example.kotlin_demoapp.databinding.ActivityComponentsBinding
import com.example.kotlin_demoapp.enums.Components

class ComponentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityComponentsBinding
    private val componentList: Array<Components> by lazy { Components.values() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityComponentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener { finish() }
        binding.componentRecyclerView.adapter = ComponentRecyclerAdapter(this, componentList)
        binding.componentRecyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                super.getItemOffsets(outRect, view, parent, state)
                outRect.set(
                    resources.getDimensionPixelSize(R.dimen.dp_twenty),
                    resources.getDimensionPixelSize(R.dimen.dp_five),
                    resources.getDimensionPixelSize(R.dimen.dp_twenty),
                    resources.getDimensionPixelSize(R.dimen.dp_five)
                )
            }
        })
    }
}