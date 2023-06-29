package com.example.kotlin_demoapp.tagb.module.authentication.view

import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.databinding.ActivityOnBoardBinding
import com.example.kotlin_demoapp.tagb.base_classes.BaseActivity
import com.example.kotlin_demoapp.tagb.helper.launchActivity
import com.example.kotlin_demoapp.tagb.helper.setStatusBarLightAppearance
import com.example.kotlin_demoapp.tagb.module.authentication.viewModel.OnBoardViewModel
import dagger.hilt.android.AndroidEntryPoint

class OnBoardActivity : BaseActivity<ActivityOnBoardBinding, OnBoardViewModel>() {

    override fun setViewModel(): OnBoardViewModel? =
        ViewModelProvider(this)[OnBoardViewModel::class.java]

    override fun getResId(): Int = R.layout.activity_on_board

    override fun setUpView() {
        setStatusBarLightAppearance(true)
        binding.viewModel = viewModel
        addObserver()
    }

    private fun addObserver() {
        viewModel.navigation.observe(this) {
            when (it) {
                OnBoardViewModel.NavigationEvent.GoToLogin -> launchActivity<LoginActivity>()
                OnBoardViewModel.NavigationEvent.GoToSignUp -> launchActivity<SignUpActivity>()
            }
        }
    }
}