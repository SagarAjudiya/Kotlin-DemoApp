package com.example.kotlin_demoapp.tagb.module.dashboard.repository

import com.example.kotlin_demoapp.tagb.api_services.MovieService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val service: MovieService
) {

    fun getMovieData(accessToken: String, pageNumber: Int) = flow {
        emit(service.getMovie(accessToken, pageNumber))
    }.flowOn(Dispatchers.IO)
}