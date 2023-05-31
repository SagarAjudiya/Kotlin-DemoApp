package com.example.kotlin_demoapp.screens.on_cloud.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.databinding.FragmentOnCloudChatBinding
import com.google.android.material.tabs.TabLayout

class OnCloudChatFragment : Fragment() {

    private lateinit var binding: FragmentOnCloudChatBinding
    private val navHost by lazy {
        childFragmentManager.findFragmentById(R.id.tabFragmentContainer) as? NavHostFragment
    }
    private var selectedTab = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnCloudChatBinding.inflate(layoutInflater)
        binding.chatTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        if (selectedTab != 0) {
                                navHost?.navController?.navigate(R.id.directMessageFragment)
                                selectedTab = 0
                        }
                    }
                    1 -> {
                        if (selectedTab != 1) {
                            navHost?.navController?.navigate(R.id.channelFragment)
                            selectedTab = 1
                        }
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
        return binding.root
    }
}