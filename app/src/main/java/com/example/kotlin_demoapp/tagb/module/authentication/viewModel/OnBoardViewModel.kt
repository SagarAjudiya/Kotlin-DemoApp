package com.example.kotlin_demoapp.tagb.module.authentication.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlin_demoapp.tagb.base_classes.BaseViewModel

class OnBoardViewModel : BaseViewModel() {

    private var _navigation = MutableLiveData<NavigationEvent>()
    val navigation: LiveData<NavigationEvent>
        get() = _navigation

    sealed class NavigationEvent {
        object GoToLogin : NavigationEvent()
        object GoToSignUp : NavigationEvent()
    }

    fun goToLogin() {
        _navigation.value = NavigationEvent.GoToLogin
    }

    fun goToSignUp() {
        _navigation.value = NavigationEvent.GoToSignUp
    }
}
