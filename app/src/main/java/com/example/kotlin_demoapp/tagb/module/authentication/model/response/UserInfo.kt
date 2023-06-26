package com.example.kotlin_demoapp.tagb.module.authentication.model.response

import com.google.gson.annotations.SerializedName

data class UserInfo(
    val id: Int,
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String
)
