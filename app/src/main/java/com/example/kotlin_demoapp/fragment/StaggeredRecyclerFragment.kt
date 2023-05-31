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

class StaggeredRecyclerFragment : Fragment() {

    private lateinit var binding: FragmentStaggeredRecyclerBinding
    private lateinit var cityArray: ArrayList<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentStaggeredRecyclerBinding.inflate(layoutInflater)

        cityArray = arrayListOf("London", "New York\n\n\n\n\n\nNey York", "Surat\n\n\n\n\n\nSurat", "Delhi\n\nDelhi", "Tokyo", "Dubai", "Mumbai", "Ahmedabad\n\n\n\n\nAhmedabad")
        binding.staggeredRecycler.layoutManager = StaggeredGridLayoutManager(2,RecyclerView.VERTICAL)
        binding.staggeredRecycler.adapter = StaggeredRecyclerAdapter(cityArray)

        return binding.root
    }
}