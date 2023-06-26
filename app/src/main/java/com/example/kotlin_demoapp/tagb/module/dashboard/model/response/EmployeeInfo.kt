package com.example.kotlin_demoapp.tagb.module.dashboard.model.response

import com.google.gson.annotations.SerializedName

data class EmployeeInfo(
    val id: String,
    val name: String,
    @SerializedName("createdAt")
    var createdDate: String,
    @SerializedName("avatar")
    var image: String
)
