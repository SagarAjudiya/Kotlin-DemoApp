package com.example.kotlin_demoapp.tagb.module.authentication.viewModel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.tagb.base_classes.BaseViewModel
import com.example.kotlin_demoapp.tagb.base_classes.Failure
import com.example.kotlin_demoapp.tagb.base_classes.Result
import com.example.kotlin_demoapp.tagb.base_classes.Success
import com.example.kotlin_demoapp.tagb.helper.isValidEmail
import com.example.kotlin_demoapp.tagb.module.authentication.model.request.LoginRequestModel
import com.example.kotlin_demoapp.tagb.module.authentication.model.response.LoginResponseModel
import com.example.kotlin_demoapp.tagb.module.authentication.repository.AuthenticationRepository
import com.example.kotlin_demoapp.tagb.utils.App
import com.example.kotlin_demoapp.tagb.utils.UserPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthenticationRepository
) : BaseViewModel() {

    val email = MutableLiveData<String>(UserPreference.username)
    val password = MutableLiveData<String>()
    lateinit var startAnimating: () -> Unit
    private val _loginSuccess = MutableLiveData<LoginResponseModel>()
    val loginSuccess: LiveData<LoginResponseModel>
        get() = _loginSuccess
    private val _loginFailure = MutableLiveData<String>()
    val loginFailure: LiveData<String>
        get() = _loginFailure

    @SuppressLint("NullSafeMutableLiveData")
    private fun doLogin(requestModel: LoginRequestModel) {
        viewModelScope.launch {
            repository.loginUser(requestModel)
                .catch {
                    _loginFailure.postValue(it.localizedMessage)
                }
                .collect {
                    when (it) {
                        is Success<*> -> {
                            val data = it.model as LoginResponseModel
                            _loginSuccess.postValue(data)
                            UserPreference.isUserLogin = true
                            UserPreference.authToken = data.authToken
                            UserPreference.refreshToken = data.refreshToken
                            UserPreference.username = data.user.email
                            UserPreference.name = "${data.user.firstName} ${data.user.lastName}"
                        }
                        is Failure -> _loginFailure.postValue(it.message)
                    }
                }
        }
    }

    fun validateLogin() {
        startAnimating()
        val email = email.value ?: ""
        val password = password.value ?: ""
        if (email.isEmpty()) {
            _loginFailure.value = App.appContext.getString(R.string.emptyEmail)
        } else if (!email.isValidEmail) {
            _loginFailure.value = App.appContext.getString(R.string.invalidEmail)
        } else if (password.isEmpty()) {
            _loginFailure.value = App.appContext.getString(R.string.emptyPassword)
        } else {
            doLogin(LoginRequestModel(email, password))
        }
    }
}