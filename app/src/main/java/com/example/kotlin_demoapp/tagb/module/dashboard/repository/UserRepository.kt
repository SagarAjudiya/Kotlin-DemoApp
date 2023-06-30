package com.example.kotlin_demoapp.tagb.module.dashboard.repository

import com.example.kotlin_demoapp.tagb.api_services.UserService
import com.example.kotlin_demoapp.tagb.base_classes.Failure
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import com.example.kotlin_demoapp.tagb.base_classes.Success
import com.example.kotlin_demoapp.tagb.helper.Constants
import org.json.JSONObject
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val service: UserService
) {

    suspend fun getUser() = flow {
        val response = service.getUser()
        if (response.isSuccessful) {
            emit(Success(response.body()?.data))
        } else {
            emit(Failure(JSONObject(response.errorBody()?.string() ?: "").getString(Constants.MESSAGE)))
        }
    }.flowOn(Dispatchers.IO)
}