package com.example.kotlin_demoapp.tagb.module.authentication.model.request

data class LoginRequestModel(
    val email: String,
    val password: String,
    val userType: String = "business"
)
