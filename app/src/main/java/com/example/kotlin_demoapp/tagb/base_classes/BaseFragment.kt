package com.example.kotlin_demoapp.tagb.base_classes

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment<Binding : ViewDataBinding, ViewModel : BaseViewModel> :
    Fragment() {

    lateinit var binding: Binding
    lateinit var viewModel: ViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, getResId(), container, false)

        setViewModel().also {
            if (it != null) {
                viewModel = it
            }
        }
        with(binding) {
            lifecycleOwner = activity
        }

        setUpView()

        return binding.root
    }

    abstract fun setViewModel(): ViewModel?

    abstract fun getResId(): Int

    open fun setUpView() {}

    open fun showError(error: String) {
        Snackbar.make(
            binding.root,
            error,
            Snackbar.LENGTH_SHORT,
        ).setBackgroundTint(Color.RED).show()
    }

    open fun showToast(text: String) {
        Toast.makeText(activity, text, Toast.LENGTH_LONG).show()
    }
}