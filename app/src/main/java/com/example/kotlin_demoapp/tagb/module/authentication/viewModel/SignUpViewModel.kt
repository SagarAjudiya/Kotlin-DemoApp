package com.example.kotlin_demoapp.tagb.module.authentication.viewModel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.tagb.base_classes.BaseViewModel
import com.example.kotlin_demoapp.tagb.base_classes.Result
import com.example.kotlin_demoapp.tagb.helper.isValidEmail
import com.example.kotlin_demoapp.tagb.module.authentication.model.request.SignUpRequestModel
import com.example.kotlin_demoapp.tagb.module.authentication.model.response.LoginResponseModel
import com.example.kotlin_demoapp.tagb.module.authentication.repository.AuthenticationRepository
import com.example.kotlin_demoapp.tagb.utils.App
import com.example.kotlin_demoapp.tagb.utils.UserPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: AuthenticationRepository
) : BaseViewModel() {

    val firstName = MutableLiveData<String>()
    val lastName = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val mobile = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val newsLetterAlert = MutableLiveData<Boolean>()
    lateinit var startAnimating: () -> Unit
    private val _signUpSuccess = MutableLiveData<LoginResponseModel>()
    val signUpSuccess: LiveData<LoginResponseModel>
        get() = _signUpSuccess
    private val _signUpFailure = MutableLiveData<String>()
    val signUpFailure: LiveData<String>
        get() = _signUpFailure

    @SuppressLint("NullSafeMutableLiveData")
    private fun doSignUp(requestModel: SignUpRequestModel) {
        viewModelScope.launch {
            repository.signUpUser(requestModel)
                .catch {
                    _signUpFailure.postValue(it.localizedMessage)
                }
                .collect {
                    when (it) {
                        is Result.Success<*> -> {
                            val data = it.model as LoginResponseModel
                            _signUpSuccess.postValue(data)
                            UserPreference.isUserLogin = true
                            UserPreference.authToken = data.authToken
                            UserPreference.refreshToken = data.refreshToken
                            UserPreference.username = data.user.email
                            UserPreference.name = "${data.user.firstName} ${data.user.lastName}"
                        }
                        is Result.Failure -> _signUpFailure.postValue(it.message)
                    }
                }
        }
    }

    fun validateSignUp() {
        startAnimating()
        val firstName = firstName.value ?: ""
        val lastName = lastName.value ?: ""
        val email = email.value ?: ""
        val mobile = mobile.value ?: ""
        val password = password.value ?: ""
        if (firstName.isEmpty()) {
            _signUpFailure.value = App.appContext.getString(R.string.emptyFirstName)
        } else if (lastName.isEmpty()) {
            _signUpFailure.value = App.appContext.getString(R.string.emptyLastName)
        } else if (email.isEmpty()) {
            _signUpFailure.value = App.appContext.getString(R.string.emptyEmail)
        } else if (!email.isValidEmail) {
            _signUpFailure.value = App.appContext.getString(R.string.invalidEmail)
        } else if (mobile.isEmpty()) {
            _signUpFailure.value = App.appContext.getString(R.string.emptyMobile)
        } else if (password.isEmpty()) {
            _signUpFailure.value = App.appContext.getString(R.string.emptyPassword)
        } else {
            doSignUp(
                SignUpRequestModel(
                    firstName, lastName, email, mobile, password
                )
            )
        }
    }
}