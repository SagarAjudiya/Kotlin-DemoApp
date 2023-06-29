package com.example.kotlin_demoapp.tagb.api_services

import com.example.kotlin_demoapp.tagb.helper.Endpoints
import com.example.kotlin_demoapp.tagb.module.dashboard.model.BasePageResponse
import com.example.kotlin_demoapp.tagb.module.dashboard.model.response.MovieInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MovieService {
    @GET(Endpoints.GET_MOVIES)
    suspend fun getMovie(
        @Header("Authorization") accessToken: String,
        @Query("page") page: Int,
        @Query("language") language: String = "en-US"
    ): BasePageResponse<List<MovieInfo>>
}