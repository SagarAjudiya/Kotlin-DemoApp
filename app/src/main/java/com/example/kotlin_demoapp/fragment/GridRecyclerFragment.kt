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

class GridRecyclerFragment : Fragment() {

    private lateinit var binding: FragmentGridRecyclerBinding
    private lateinit var cityArray: ArrayList<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentGridRecyclerBinding.inflate(layoutInflater)

        cityArray = arrayListOf("London", "New York", "Surat", "Delhi", "Tokyo\n\n\n\n\n\n\nTokyo", "Dubai", "Mumbai", "Ahmedabad", "London", "New York", "Surat", "Delhi", "Tokyo", "Dubai", "Mumbai", "Ahmedabad")
        binding.gridRecycler.layoutManager = GridLayoutManager(container?.context,2)
        binding.gridRecycler.adapter = GridRecyclerAdapter(cityArray)

        return binding.root
    }
}