package com.example.kotlin_demoapp.tagb.module.authentication.model.response

import com.google.gson.annotations.SerializedName

data class LoginResponseModel(
    @SerializedName("User")
    val user: UserInfo,
    val authToken: String,
    val refreshToken: String
)
