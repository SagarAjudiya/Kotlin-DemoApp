package com.example.kotlin_demoapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.kotlin_demoapp.fragment.ExpendableRecyclerFragment
import com.example.kotlin_demoapp.fragment.GridRecyclerFragment
import com.example.kotlin_demoapp.fragment.LinearRecyclerFragment
import com.example.kotlin_demoapp.fragment.SearchRecyclerViewFragment
import com.example.kotlin_demoapp.fragment.StaggeredRecyclerFragment
import com.example.kotlin_demoapp.fragment.SwipeRecyclerFragment

class RecyclerTabLayoutAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 6
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return LinearRecyclerFragment()
            1 -> return GridRecyclerFragment()
            2 -> return StaggeredRecyclerFragment()
            3 -> return SearchRecyclerViewFragment()
            4 -> return ExpendableRecyclerFragment()
            5 -> return SwipeRecyclerFragment()
        }
        return LinearRecyclerFragment()
    }
}