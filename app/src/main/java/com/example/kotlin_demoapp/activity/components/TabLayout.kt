package com.example.kotlin_demoapp.activity.components

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.kotlin_demoapp.adapter.TabLayoutAdapter
import com.example.kotlin_demoapp.databinding.ActivityTabLayoutBinding
import com.google.android.material.tabs.TabLayout

class TabLayout : AppCompatActivity() {

    private lateinit var binding: ActivityTabLayoutBinding
    private lateinit var pageAdapter: TabLayoutAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTabLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        pageAdapter = TabLayoutAdapter(this)
        binding.fragmentPager.adapter = pageAdapter

        binding.mainTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val safeTab = tab ?: return
                binding.fragmentPager.currentItem = safeTab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        binding.fragmentPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val tab = binding.mainTabLayout.getTabAt(position) ?: return
                tab.select()
            }
        })
    }
}