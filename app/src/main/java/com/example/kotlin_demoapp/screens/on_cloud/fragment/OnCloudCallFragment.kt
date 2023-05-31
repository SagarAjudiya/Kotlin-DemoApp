package com.example.kotlin_demoapp.screens.on_cloud.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.databinding.FragmentOnCloudCallBinding
import com.example.kotlin_demoapp.screens.on_cloud.OnCloudConstants
import com.example.kotlin_demoapp.screens.on_cloud.callback.OnFragmentChange
import com.google.android.material.tabs.TabLayout

class OnCloudCallFragment : Fragment(), OnFragmentChange {

    private lateinit var binding: FragmentOnCloudCallBinding
    private val navHost by lazy {
        childFragmentManager.findFragmentById(R.id.mainCallFragment) as? NavHostFragment
    }
    private var tabChangeByListener = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentOnCloudCallBinding.inflate(layoutInflater)
        binding.callTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tabChangeByListener) {
                    tabChangeByListener = false
                    return
                }
                when (tab?.position) {
                    OnCloudConstants.PINNED -> {
                        navHost?.navController?.navigate(R.id.pinnedFragment)
                    }
                    OnCloudConstants.FILES -> {
                        navHost?.navController?.navigate(R.id.filesFragment)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
        return binding.root
    }

    override fun changeTab(toPosition: Int) {
        when (toPosition) {
            OnCloudConstants.PINNED -> {
                binding.callTabLayout.getTabAt(toPosition)?.select()
            }
            OnCloudConstants.FILES -> {
                binding.callTabLayout.getTabAt(toPosition)?.select()
            }
        }
        tabChangeByListener = true
    }
}