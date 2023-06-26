package com.example.kotlin_demoapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_demoapp.adapter.LinearRecyclerAdapter
import com.example.kotlin_demoapp.databinding.FragmentLinearRecyclerBinding
import com.example.kotlin_demoapp.helper.Helper

class LinearRecyclerFragment : Fragment() {

    private lateinit var binding: FragmentLinearRecyclerBinding
    private lateinit var cityArray: ArrayList<String>
    private lateinit var adapter: LinearRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLinearRecyclerBinding.inflate(layoutInflater)

        cityArray = Helper.getCityNameList()
        adapter = LinearRecyclerAdapter().also { it.setData(cityArray) }
        binding.linearRecycler.adapter = adapter

        binding.btnAdd.setOnClickListener {
            val text = binding.txtNewData.text.toString()
            if (text.isEmpty()) return@setOnClickListener
            cityArray.add(text)
            adapter.setData(cityArray)
        }

        binding.btnClear.setOnClickListener {
            cityArray.clear()
            adapter.setData(cityArray)
        }

        return binding.root
    }
}