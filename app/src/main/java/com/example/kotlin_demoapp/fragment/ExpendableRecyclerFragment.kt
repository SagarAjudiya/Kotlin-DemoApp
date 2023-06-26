package com.example.kotlin_demoapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.adapter.ExpendableRecyclerAdapter
import com.example.kotlin_demoapp.databinding.FragmentExpendableRecyclerViewBinding
import com.example.kotlin_demoapp.databinding.FragmentSearchRecyclerViewBinding
import com.example.kotlin_demoapp.helper.Helper
import com.example.kotlin_demoapp.model.CityDescription

class ExpendableRecyclerFragment : Fragment() {

    private lateinit var binding: FragmentExpendableRecyclerViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentExpendableRecyclerViewBinding.inflate(layoutInflater)
        binding.expendableRecycler.adapter = ExpendableRecyclerAdapter(Helper.getCityList())

        return binding.root
    }
}