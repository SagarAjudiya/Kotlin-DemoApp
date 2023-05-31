package com.example.kotlin_demoapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.kotlin_demoapp.fragment.HomeFragment
import com.example.kotlin_demoapp.fragment.NotificationFragment
import com.example.kotlin_demoapp.fragment.SettingFragment

class TabLayoutAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        when(position) {
            0 -> return HomeFragment()
            1 -> return NotificationFragment()
            2 -> return SettingFragment()
        }
        return HomeFragment()
    }
}