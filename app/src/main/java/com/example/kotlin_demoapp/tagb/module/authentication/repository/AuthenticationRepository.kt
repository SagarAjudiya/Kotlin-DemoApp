package com.example.kotlin_demoapp.tagb.module.authentication.repository

import com.example.kotlin_demoapp.tagb.api_services.AuthenticationService
import com.example.kotlin_demoapp.tagb.base_classes.Failure
import com.example.kotlin_demoapp.tagb.base_classes.Success
import com.example.kotlin_demoapp.tagb.helper.Constants
import com.example.kotlin_demoapp.tagb.module.authentication.model.request.LoginRequestModel
import com.example.kotlin_demoapp.tagb.module.authentication.model.request.SignUpRequestModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.json.JSONObject
import javax.inject.Inject

class AuthenticationRepository @Inject constructor(
    private val service: AuthenticationService
) {

    suspend fun loginUser(requestModel: LoginRequestModel) = flow {
        val response = service.loginUser(requestModel)
        if (response.isSuccessful) {
            emit(Success(response.body()?.data))
        } else {
            emit(
                Failure(
                    JSONObject(
                        response.errorBody()?.string() ?: ""
                    ).getString(Constants.MESSAGE)
                )
            )
        }
    }.flowOn(Dispatchers.IO)

    suspend fun signUpUser(requestModel: SignUpRequestModel) = flow {
        val response = service.signUpUser(requestModel)
        if (response.isSuccessful) {
            emit(Success(response.body()?.data))
        } else {
            emit(
                Failure(
                    JSONObject(
                        response.errorBody()?.string() ?: ""
                    ).getString(Constants.MESSAGE)
                )
            )
        }
    }.flowOn(Dispatchers.IO)
}