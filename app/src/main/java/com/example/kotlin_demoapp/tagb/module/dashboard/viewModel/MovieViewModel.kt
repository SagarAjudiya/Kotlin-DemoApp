package com.example.kotlin_demoapp.tagb.module.dashboard.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlin_demoapp.tagb.base_classes.BaseViewModel
import com.example.kotlin_demoapp.tagb.module.dashboard.model.BasePageResponse
import com.example.kotlin_demoapp.tagb.module.dashboard.model.response.MovieInfo
import com.example.kotlin_demoapp.tagb.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel() : BaseViewModel() {

    private val _fetchListSuccess = MutableLiveData<List<MovieInfo>>()
    val fetchListSuccess: LiveData<List<MovieInfo>>
        get() = _fetchListSuccess
    private val _fetchListFailure = MutableLiveData<String>()
    val fetchListFailure: LiveData<String>
        get() = _fetchListFailure

    fun getMovieList(accessToken: String, page: Int) {
        Repository.getMovieData(accessToken, page)
            .enqueue(object : Callback<BasePageResponse<List<MovieInfo>>> {
                override fun onResponse(
                    call: Call<BasePageResponse<List<MovieInfo>>>?,
                    response: Response<BasePageResponse<List<MovieInfo>>>?
                ) {
                    if (response != null && response.isSuccessful) {
                        _fetchListSuccess.value = response.body().result.toMutableList()
                    } else {
                        _fetchListFailure.value = response.toString()
                    }
                }

                override fun onFailure(
                    call: Call<BasePageResponse<List<MovieInfo>>>?,
                    t: Throwable?
                ) {
                    _fetchListFailure.value = t?.localizedMessage
                }
            })
    }
}