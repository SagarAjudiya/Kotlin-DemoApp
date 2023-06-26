package com.example.kotlin_demoapp.tagb.module.dashboard.model.response

import com.google.gson.annotations.SerializedName

data class MovieInfo(
    @SerializedName("original_title")
    val name: String,
    @SerializedName("release_date")
    val date: String,
    @SerializedName("poster_path")
    val image: String
)
