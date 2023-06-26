package com.example.kotlin_demoapp.tagb.module.dashboard.model

import com.google.gson.annotations.SerializedName

data class BasePageResponse<T>(
    val page: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("results")
    val result: T
)