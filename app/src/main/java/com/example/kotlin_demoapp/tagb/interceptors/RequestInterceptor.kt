package com.example.kotlin_demoapp.tagb.interceptors

import com.example.kotlin_demoapp.tagb.helper.HeaderValue
import com.example.kotlin_demoapp.tagb.helper.RequestHeader
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        request.addHeader(RequestHeader.APP_VERSION, HeaderValue.APP_VERSION)
            .addHeader(RequestHeader.PLATEFORM, HeaderValue.PLATEFORM)
        return chain.proceed(request.build())
    }
}