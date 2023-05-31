package com.example.kotlin_demoapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_demoapp.adapter.SwipeRecyclerAdapter
import com.example.kotlin_demoapp.databinding.FragmentSwipeRecyclerViewBinding
import com.google.android.material.snackbar.Snackbar

class SwipeRecyclerFragment : Fragment() {

    private lateinit var binding: FragmentSwipeRecyclerViewBinding
    private lateinit var swipeAdapter: SwipeRecyclerAdapter
    private lateinit var cityArray: ArrayList<String>
    private lateinit var deletedData: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSwipeRecyclerViewBinding.inflate(layoutInflater)

        cityArray = arrayListOf("London", "New York", "Surat", "Delhi", "Tokyo", "Dubai", "Mumbai", "Ahmedabad")
        binding.swipeRecyclerView.layoutManager = LinearLayoutManager(container?.context)
        swipeAdapter = SwipeRecyclerAdapter(cityArray)
        binding.swipeRecyclerView.adapter = swipeAdapter

        ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                deletedData = cityArray[position]
                cityArray.removeAt(position)
                swipeAdapter.notifyDataSetChanged()

                Snackbar.make(binding.swipeRecyclerView,deletedData + " deleted", Snackbar.LENGTH_LONG).setAction("UNDO",View.OnClickListener {
                    cityArray.add(position,deletedData)
                    swipeAdapter.notifyDataSetChanged()
                }).show()
            }
        }).attachToRecyclerView(binding.swipeRecyclerView)
        return binding.root
    }
}