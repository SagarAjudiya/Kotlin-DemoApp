package com.example.kotlin_demoapp.tagb.module.authentication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.tagb.api_services.AuthenticationService
import com.example.kotlin_demoapp.tagb.base_classes.Result
import com.example.kotlin_demoapp.tagb.helper.Constants
import com.example.kotlin_demoapp.tagb.module.authentication.model.BaseResponseModel
import com.example.kotlin_demoapp.tagb.module.authentication.model.request.LoginRequestModel
import com.example.kotlin_demoapp.tagb.module.authentication.model.request.SignUpRequestModel
import com.example.kotlin_demoapp.tagb.module.authentication.model.response.LoginResponseModel
import com.example.kotlin_demoapp.tagb.utils.App
import com.example.kotlin_demoapp.tagb.utils.UserPreference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class AuthenticationRepository @Inject constructor(
    private val service: AuthenticationService
) {

    suspend fun loginUser(requestModel: LoginRequestModel) = flow {
        val response = service.loginUser(requestModel)
        if (response.isSuccessful) {
            emit(Result.Success(response.body()?.data))
        } else {
            emit(Result.Failure(JSONObject(response.errorBody()?.string()).getString(Constants.MESSAGE)))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun signUpUser(requestModel: SignUpRequestModel) = flow {
        val response = service.signUpUser(requestModel)
        if (response.isSuccessful) {
            emit(Result.Success(response.body()?.data))
        } else {
            emit(Result.Failure(JSONObject(response.errorBody()?.string()).getString(Constants.MESSAGE)))
        }
    }.flowOn(Dispatchers.IO)
}