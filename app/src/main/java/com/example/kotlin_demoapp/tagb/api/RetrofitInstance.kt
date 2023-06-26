package com.example.kotlin_demoapp.tagb.api

import com.example.kotlin_demoapp.tagb.helper.HeaderValue
import com.example.kotlin_demoapp.tagb.helper.RequestHeader
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
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

    val api: SimpleApi by lazy {
        retrofit.create(SimpleApi::class.java)
    }

    class RequestInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request().newBuilder()
            request.addHeader(RequestHeader.APP_VERSION, HeaderValue.APP_VERSION)
                .addHeader(RequestHeader.PLATEFORM, HeaderValue.PLATEFORM)
            return chain.proceed(request.build())
        }
    }
}