package com.example.kotlin_demoapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.databinding.FragmentHomeBinding
import com.example.kotlin_demoapp.databinding.FragmentNotificationBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class NotificationFragment : Fragment() {

    private lateinit var binding: FragmentNotificationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotificationBinding.inflate(layoutInflater)
        val navBar = container?.rootView?.findViewById<BottomNavigationView>(R.id.bottomNavigationBar)
        navBar?.removeBadge(R.id.notificationNavigation)
        return binding.root
    }
}