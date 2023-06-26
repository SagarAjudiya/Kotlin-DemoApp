package com.example.kotlin_demoapp.tagb.module.authentication.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.tagb.base_classes.BaseViewModel
import com.example.kotlin_demoapp.tagb.helper.isValidEmail
import com.example.kotlin_demoapp.tagb.module.authentication.model.BaseResponseModel
import com.example.kotlin_demoapp.tagb.module.authentication.model.request.SignUpRequestModel
import com.example.kotlin_demoapp.tagb.module.authentication.model.response.LoginResponseModel
import com.example.kotlin_demoapp.tagb.repository.Repository
import com.example.kotlin_demoapp.tagb.utils.App
import com.example.kotlin_demoapp.tagb.utils.UserPreference
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignUpViewModel : BaseViewModel() {

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

    private fun doSignUp(requestModel: SignUpRequestModel) {
        Repository.signUpUser(requestModel)
            .enqueue(object : Callback<BaseResponseModel<LoginResponseModel>> {
                override fun onResponse(
                    call: Call<BaseResponseModel<LoginResponseModel>>?,
                    response: Response<BaseResponseModel<LoginResponseModel>>?
                ) {
                    if (response?.body()?.data != null && response.isSuccessful) {
                        val data = response.body().data
                        _signUpSuccess.value = data
                        UserPreference.isUserLogin = true
                        UserPreference.authToken = data.authToken
                        UserPreference.refreshToken = data.refreshToken
                        UserPreference.username = data.user.email
                        UserPreference.name = "${data.user.firstName} ${data.user.lastName}"
                    } else {
                        if (response?.errorBody() != null) {
                            try {
                                _signUpFailure.value = JSONObject(
                                    response.errorBody().string()
                                ).getString(App.appContext.getString(R.string.message))
                            } catch (e: Exception) {
                                _signUpFailure.value = e.localizedMessage
                            }
                        } else {
                            _signUpFailure.value = App.appContext.getString(R.string.nullResponse)
                        }
                    }
                }

                override fun onFailure(
                    call: Call<BaseResponseModel<LoginResponseModel>>?,
                    t: Throwable?
                ) {
                    _signUpFailure.value = t?.localizedMessage
                }
            })
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