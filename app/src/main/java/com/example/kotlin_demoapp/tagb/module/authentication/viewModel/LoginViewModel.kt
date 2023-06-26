package com.example.kotlin_demoapp.tagb.module.authentication.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.tagb.base_classes.BaseViewModel
import com.example.kotlin_demoapp.tagb.helper.isValidEmail
import com.example.kotlin_demoapp.tagb.module.authentication.model.BaseResponseModel
import com.example.kotlin_demoapp.tagb.module.authentication.model.request.LoginRequestModel
import com.example.kotlin_demoapp.tagb.module.authentication.model.response.LoginResponseModel
import com.example.kotlin_demoapp.tagb.repository.Repository
import com.example.kotlin_demoapp.tagb.utils.App
import com.example.kotlin_demoapp.tagb.utils.UserPreference
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel() : BaseViewModel() {

    val email = MutableLiveData<String>(UserPreference.username)
    val password = MutableLiveData<String>()
    lateinit var startAnimating: () -> Unit
    private val _loginSuccess = MutableLiveData<LoginResponseModel>()
    val loginSuccess: LiveData<LoginResponseModel>
        get() = _loginSuccess
    private val _loginFailure = MutableLiveData<String>()
    val loginFailure: LiveData<String>
        get() = _loginFailure

    private fun doLogin(requestModel: LoginRequestModel) {
        Repository.loginUser(requestModel)
            .enqueue(object : Callback<BaseResponseModel<LoginResponseModel>> {
                override fun onResponse(
                    call: Call<BaseResponseModel<LoginResponseModel>>?,
                    response: Response<BaseResponseModel<LoginResponseModel>>?
                ) {
                    if (response?.body()?.data != null && response.isSuccessful) {
                        val data = response.body().data
                        _loginSuccess.value = data
                        UserPreference.isUserLogin = true
                        UserPreference.authToken = data.authToken
                        UserPreference.refreshToken = data.refreshToken
                        UserPreference.username = data.user.email
                        UserPreference.name = "${data.user.firstName} ${data.user.lastName}"
                    } else {
                        if (response?.errorBody() != null) {
                            try {
                                _loginFailure.value = JSONObject(
                                    response.errorBody().string()
                                ).getString(App.appContext.getString(R.string.message))
                            } catch (e: Exception) {
                                _loginFailure.value = e.localizedMessage
                            }
                        } else {
                            _loginFailure.value = App.appContext.getString(R.string.nullResponse)
                        }
                    }
                }

                override fun onFailure(
                    call: Call<BaseResponseModel<LoginResponseModel>>?,
                    t: Throwable?
                ) {
                    _loginFailure.value = t?.localizedMessage
                }
            })
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