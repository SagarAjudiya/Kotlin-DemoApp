package com.example.kotlin_demoapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.adapter.SearchRecyclerAdapter
import com.example.kotlin_demoapp.databinding.FragmentSearchRecyclerViewBinding
import com.google.android.material.snackbar.Snackbar

class SearchRecyclerViewFragment : Fragment() {

    private lateinit var binding: FragmentSearchRecyclerViewBinding
    private lateinit var cityArray: ArrayList<String>
    private lateinit var searchAdapter: SearchRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        cityArray = arrayListOf("London", "New York", "Surat", "Delhi", "Tokyo", "Dubai", "Mumbai", "Ahmedabad")
        searchAdapter = SearchRecyclerAdapter(cityArray)
        binding = FragmentSearchRecyclerViewBinding.inflate(layoutInflater)
        binding.searchRecyclerView.layoutManager = LinearLayoutManager(container?.context)
        binding.searchRecyclerView.adapter = searchAdapter

        binding.searchField.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })
        return binding.root
    }

    private fun filterList(newText: String?) {
        val query = newText ?: return
        val filteredList = cityArray.filter {
            it.lowercase().contains(query)
        } as ArrayList<String>
        if (filteredList.isEmpty()){
            Snackbar.make(this.requireView(),"No Data Found",Snackbar.LENGTH_SHORT).show()
        } else {
            searchAdapter.setFilteredData(filteredList)
        }
    }
}