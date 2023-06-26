package com.example.kotlin_demoapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.adapter.GridRecyclerAdapter
import com.example.kotlin_demoapp.adapter.LinearRecyclerAdapter
import com.example.kotlin_demoapp.databinding.FragmentGridRecyclerBinding
import com.example.kotlin_demoapp.helper.Helper

class GridRecyclerFragment : Fragment() {

    private lateinit var binding: FragmentGridRecyclerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentGridRecyclerBinding.inflate(layoutInflater)
        binding.gridRecycler.adapter = GridRecyclerAdapter(Helper.getCityArray())

        return binding.root
    }
}