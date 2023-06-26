package com.example.kotlin_demoapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.adapter.LinearRecyclerAdapter
import com.example.kotlin_demoapp.adapter.StaggeredRecyclerAdapter
import com.example.kotlin_demoapp.databinding.FragmentStaggeredRecyclerBinding
import com.example.kotlin_demoapp.helper.Helper

class StaggeredRecyclerFragment : Fragment() {

    private lateinit var binding: FragmentStaggeredRecyclerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentStaggeredRecyclerBinding.inflate(layoutInflater)
        binding.staggeredRecycler.adapter = StaggeredRecyclerAdapter(Helper.getCityStaggeredList())

        return binding.root
    }
}