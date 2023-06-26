package com.example.kotlin_demoapp.tagb.module.authentication.view

import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.databinding.ActivityLoginBinding
import com.example.kotlin_demoapp.tagb.base_classes.BaseActivity
import com.example.kotlin_demoapp.tagb.helper.launchActivityWithClearTask
import com.example.kotlin_demoapp.tagb.helper.putDelay
import com.example.kotlin_demoapp.tagb.helper.setDrawableColor
import com.example.kotlin_demoapp.tagb.helper.setStatusBarLightAppearance
import com.example.kotlin_demoapp.tagb.module.authentication.viewModel.LoginViewModel
import com.example.kotlin_demoapp.tagb.module.dashboard.view.WebServiceActivity

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {

    override fun setViewModel(): LoginViewModel? =
        ViewModelProvider(this)[LoginViewModel::class.java]

    override fun getResId(): Int = R.layout.activity_login

    override fun setUpView() {
        setStatusBarLightAppearance(false)
        binding.viewModel = viewModel
        setupNavBar()
        addObservers()
        binding.etEmail.requestFocus()
    }

    private fun addObservers() {

        viewModel.startAnimating = {
            binding.btnLogin.startAnimation()
        }

        viewModel.loginSuccess.observe(this) {
            ContextCompat.getDrawable(this, R.drawable.icon_check)?.let { drawable ->
                drawable.setDrawableColor(R.color.deepAqua)
                binding.btnLogin.doneLoadingAnimation(
                    getColor(R.color.white), drawable.toBitmap()
                )
                putDelay(1000) {
                    launchActivityWithClearTask<WebServiceActivity>()
                }
            }
        }

        viewModel.loginFailure.observe(this) {
            ContextCompat.getDrawable(this, R.drawable.icon_close)?.let { drawable ->
                drawable.setDrawableColor(R.color.deepAqua)
                binding.btnLogin.doneLoadingAnimation(
                    getColor(R.color.white), drawable.toBitmap()
                )
                showError(it)
                putDelay(2000) {
                    binding.btnLogin.revertAnimation()
                }
            }
        }
    }

    private fun setupNavBar() {
        binding.navBarLogin.txtTitleNavbar.text = getString(R.string.login_title)
        binding.navBarLogin.btnLeftNavbar.setOnClickListener {
            finish()
        }
    }
}