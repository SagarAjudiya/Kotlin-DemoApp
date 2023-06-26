package com.example.kotlin_demoapp.activity.components

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.adapter.ViewPagerRecyclerAdapter
import com.example.kotlin_demoapp.databinding.ActivityViewPagerBinding
import com.example.kotlin_demoapp.helper.Helper
import com.example.kotlin_demoapp.model.CityDetails

class ViewPagerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewPagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityViewPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.mainViewPager.adapter = ViewPagerRecyclerAdapter(Helper.getCityListWithImageAndDescription())
    }
}