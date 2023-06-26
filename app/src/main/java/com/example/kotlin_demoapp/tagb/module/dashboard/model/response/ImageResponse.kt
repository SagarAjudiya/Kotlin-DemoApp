package com.example.kotlin_demoapp.tagb.module.dashboard.model.response

import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("files")
    val imageFiles: List<ImageFile>
)
