package com.example.kotlin_demoapp.activity.components

import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.adapter.IntentRecyclerAdapter
import com.example.kotlin_demoapp.databinding.ActivityIntentBinding
import com.example.kotlin_demoapp.enums.IntentTypes

class IntentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIntentBinding
    private lateinit var adapter: IntentRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityIntentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = IntentRecyclerAdapter()
        adapter.submitList(IntentTypes.values())
        binding.intentRecycler.adapter = adapter
        binding.intentRecycler.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
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