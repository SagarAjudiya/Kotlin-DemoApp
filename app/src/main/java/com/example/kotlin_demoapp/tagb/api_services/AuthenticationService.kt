package com.example.kotlin_demoapp.tagb.api_services

import com.example.kotlin_demoapp.tagb.helper.Endpoints
import com.example.kotlin_demoapp.tagb.module.authentication.model.BaseResponseModel
import com.example.kotlin_demoapp.tagb.module.authentication.model.request.LoginRequestModel
import com.example.kotlin_demoapp.tagb.module.authentication.model.request.SignUpRequestModel
import com.example.kotlin_demoapp.tagb.module.authentication.model.response.LoginResponseModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationService {

    @POST(Endpoints.LOGIN)
    suspend fun loginUser(
        @Body body: LoginRequestModel
    ): Response<BaseResponseModel<LoginResponseModel>>

    @POST(Endpoints.SIGNUP)
    suspend fun signUpUser(
        @Body body: SignUpRequestModel
    ): Response<BaseResponseModel<LoginResponseModel>>
}