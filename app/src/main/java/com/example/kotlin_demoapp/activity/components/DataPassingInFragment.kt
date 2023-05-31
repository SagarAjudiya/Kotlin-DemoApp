package com.example.kotlin_demoapp.activity.components

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.callbacks.ChangeNameListner
import com.example.kotlin_demoapp.databinding.ActivityDataPassingFragmentsBinding
import com.example.kotlin_demoapp.fragment.LeftIntentFragment
import com.example.kotlin_demoapp.fragment.RightIntentFragment

class DataPassingInFragment : AppCompatActivity(), ChangeNameListner {

    private lateinit var binding: ActivityDataPassingFragmentsBinding
    private var currentFragment: Fragment = LeftIntentFragment()
    private val TAG = "LIFECYCLE"

    @SuppressLint("CommitTransaction")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDataPassingFragmentsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG,"onCreate Activity")

        var fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameFragment,currentFragment)
        fragmentTransaction.commit()
        Log.d(TAG, supportFragmentManager.backStackEntryCount.toString())

        binding.btnSwitchFragment.setOnClickListener {
            fragmentTransaction = supportFragmentManager.beginTransaction()
            when(currentFragment){
                is LeftIntentFragment -> currentFragment = RightIntentFragment()
                is RightIntentFragment -> currentFragment = LeftIntentFragment()
            }
            fragmentTransaction.replace(R.id.frameFragment,currentFragment)
            fragmentTransaction.commit()
            Log.d(TAG, supportFragmentManager.backStackEntryCount.toString())
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"onStart Activity")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"onStop Activity")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"onPause Activity")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"onResume Activity")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"onDestroy Activity")
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Log.d(TAG,"onAttachedToWindow Activity")
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        Log.d(TAG,"onDetachedFromWindow Activity")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG,"onRestart Activity")
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        Log.d(TAG,"onResumeFragments Activity")
    }

    override fun nameEvent(name: String, surname: String) {
        binding.apply {
            txtTitle.text = getString(R.string.double_string_with_space,name,surname)
        }
    }

}