package com.example.kotlin_demoapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.databinding.FragmentHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val navBar by lazy { activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationBar) }
    private var itemCount: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.homeDownloadButton.setOnClickListener {
            itemCount++
            navBar?.getOrCreateBadge(R.id.notificationNavigation)?.number = itemCount
        }

        return binding.root
    }
}