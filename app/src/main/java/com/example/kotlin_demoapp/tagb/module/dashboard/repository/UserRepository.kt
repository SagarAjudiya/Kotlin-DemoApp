package com.example.kotlin_demoapp.tagb.module.dashboard.repository

import com.example.kotlin_demoapp.tagb.api_services.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val service: UserService
) {

    suspend fun getUser() = flow {
        emit(service.getUser())
    }.flowOn(Dispatchers.IO)
}