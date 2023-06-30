package com.example.kotlin_demoapp.tagb.api_services

import com.example.kotlin_demoapp.tagb.helper.Endpoints
import com.example.kotlin_demoapp.tagb.module.dashboard.model.BaseUserResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface UserService {
    @GET(Endpoints.GET_USER)
    suspend fun getUser(): Response<BaseUserResponse>
}