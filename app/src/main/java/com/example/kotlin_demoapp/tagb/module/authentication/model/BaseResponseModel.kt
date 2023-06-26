package com.example.kotlin_demoapp.tagb.module.authentication.model

data class BaseResponseModel<T>(
    val success: Boolean,
    val message: String,
    val data: T
)
