package com.example.kotlin_demoapp.tagb.module.authentication.view

import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.databinding.ActivitySignUpBinding
import com.example.kotlin_demoapp.tagb.base_classes.BaseActivity
import com.example.kotlin_demoapp.tagb.helper.launchActivityWithClearTask
import com.example.kotlin_demoapp.tagb.helper.putDelay
import com.example.kotlin_demoapp.tagb.helper.setDrawableColor
import com.example.kotlin_demoapp.tagb.helper.setStatusBarLightAppearance
import com.example.kotlin_demoapp.tagb.module.authentication.viewModel.SignUpViewModel
import com.example.kotlin_demoapp.tagb.module.dashboard.view.WebServiceActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : BaseActivity<ActivitySignUpBinding, SignUpViewModel>() {

    override fun setViewModel(): SignUpViewModel? =
        ViewModelProvider(this)[SignUpViewModel::class.java]

    override fun getResId(): Int = R.layout.activity_sign_up

    override fun setUpView() {
        setStatusBarLightAppearance(false)
        binding.viewModel = viewModel
        setupTermCondition()
        setupNavBar()
        addObservers()
        binding.etFirstName.requestFocus()
    }

    private fun setupTermCondition() {
        val spannableString = SpannableString(getString(R.string.term_and_condition))
        val termString = "Terms of Use"
        val privacyString = "Privacy Policy"
        val termStartIndex = getString(R.string.term_and_condition).indexOf(termString, 0)
        val privacyStartIndex = getString(R.string.term_and_condition).indexOf(privacyString, 0)
        val termClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                showToast("Terms of Use")
                binding.root.invalidate()
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = true
                ds.color = getColor(R.color.white)
                binding.txtTermCondition.invalidate()
            }
        }
        val privacyClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                showToast("Terms of Use")
                binding.root.invalidate()
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = true
                ds.color = getColor(R.color.white)
                binding.txtTermCondition.invalidate()
            }
        }
        spannableString.apply {
            setSpan(
                termClickableSpan, termStartIndex, termStartIndex + termString.length,
                Spanned.SPAN_INCLUSIVE_INCLUSIVE
            )
            setSpan(
                privacyClickableSpan, privacyStartIndex, privacyStartIndex + privacyString.length,
                Spanned.SPAN_INCLUSIVE_INCLUSIVE
            )
        }
        binding.txtTermCondition.highlightColor = ContextCompat.getColor(this, R.color.transparent)
        binding.txtTermCondition.movementMethod = LinkMovementMethod.getInstance()
        binding.txtTermCondition.text = spannableString
    }

    private fun addObservers() {

        viewModel.startAnimating = {

            binding.btnSignUp.startAnimation()
        }

        viewModel.signUpSuccess.observe(this) {
            ContextCompat.getDrawable(this, R.drawable.icon_check)?.let { drawable ->
                drawable.setDrawableColor(R.color.deepAqua)
                binding.btnSignUp.doneLoadingAnimation(
                    getColor(R.color.white), drawable.toBitmap()
                )
                putDelay(1000) {
                    launchActivityWithClearTask<WebServiceActivity>()
                }
            }
        }

        viewModel.signUpFailure.observe(this) {
            ContextCompat.getDrawable(this, R.drawable.icon_close)?.let { drawable ->
                drawable.setDrawableColor(R.color.deepAqua)
                binding.btnSignUp.doneLoadingAnimation(
                    getColor(R.color.white), drawable.toBitmap()
                )
                showError(it)
                putDelay(2000) {
                    binding.btnSignUp.revertAnimation()
                }
            }
        }
    }

    private fun setupNavBar() {
        binding.navBarSignUp.txtTitleNavbar.text = getString(R.string.sign_up_title)
        binding.navBarSignUp.btnLeftNavbar.setOnClickListener {
            finish()
        }
    }
}