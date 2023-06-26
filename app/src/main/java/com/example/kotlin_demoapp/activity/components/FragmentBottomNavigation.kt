package com.example.kotlin_demoapp.activity.components

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.databinding.ActivityBottomNavigationBinding
import com.example.kotlin_demoapp.fragment.HomeFragment
import com.example.kotlin_demoapp.fragment.NotificationFragment
import com.example.kotlin_demoapp.fragment.SettingFragment

class FragmentBottomNavigation : AppCompatActivity() {

    private lateinit var binding: ActivityBottomNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())

        binding.bottomNavigationBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.homeNavigation -> replaceFragment(HomeFragment())
                R.id.settingNavigation -> replaceFragment(SettingFragment())
                R.id.notificationNavigation -> replaceFragment(NotificationFragment())
                else -> replaceFragment(HomeFragment())
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.initialView,fragment)
        fragmentTransaction.commit()
    }
}