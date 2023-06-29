package com.example.kotlin_demoapp.tagb.api_services

import com.example.kotlin_demoapp.tagb.interceptors.RequestInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RetrofitInstance(private val baseURL: String) {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder().apply {
                    connectTimeout(1, TimeUnit.MINUTES)
                    writeTimeout(1, TimeUnit.MINUTES)
                    readTimeout(1, TimeUnit.MINUTES)
                    addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    addInterceptor(RequestInterceptor())
                }.build()
            )
            .build()
    }

    val api: ImageService by lazy {
        retrofit.create(ImageService::class.java)
    }
}