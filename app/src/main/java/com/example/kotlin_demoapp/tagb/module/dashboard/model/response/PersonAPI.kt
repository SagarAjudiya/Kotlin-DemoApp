package com.example.kotlin_demoapp.tagb.module.dashboard.model.response

import com.google.gson.annotations.SerializedName

data class PersonAPI(
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    val email: String,
    @SerializedName("avatar")
    val image: String
)
