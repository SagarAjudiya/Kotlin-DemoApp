package com.example.kotlin_demoapp.tagb.api_services

import com.example.kotlin_demoapp.tagb.helper.Endpoints
import com.example.kotlin_demoapp.tagb.module.dashboard.model.response.ImageResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ImageService {

    @Multipart
    @POST(Endpoints.FORM_DATA)
    suspend fun uploadImage(
        @Header("Authorization") accessToken: String,
        @Part image: MultipartBody.Part
    ): ImageResponse
}