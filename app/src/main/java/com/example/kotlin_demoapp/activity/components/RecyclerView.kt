package com.example.kotlin_demoapp.activity.components

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.kotlin_demoapp.adapter.RecyclerTabLayoutAdapter
import com.example.kotlin_demoapp.databinding.ActivityRecyclerViewBinding
import com.google.android.material.tabs.TabLayout

class RecyclerView: AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerViewBinding
    private lateinit var pagerAdapter: RecyclerTabLayoutAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        pagerAdapter = RecyclerTabLayoutAdapter(this)
        binding.recyclerPager.adapter = pagerAdapter

        binding.recyclerTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val safeTab = tab ?: return
                binding.recyclerPager.currentItem = safeTab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        binding.recyclerPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val tab = binding.recyclerTabLayout.getTabAt(position) ?: return
                tab.select()
            }
        })

    }
}