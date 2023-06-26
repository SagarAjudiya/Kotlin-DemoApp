package com.example.kotlin_demoapp.tagb.module.dashboard.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlin_demoapp.tagb.base_classes.BaseViewModel

class WebServicesViewModel : BaseViewModel() {

    private var _isDrawerOpen = MutableLiveData<Boolean>()
    val isDrawerOpen: LiveData<Boolean>
        get() = _isDrawerOpen

    fun btnDrawableClicked() {
        _isDrawerOpen.value = true
    }
}