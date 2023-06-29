package com.example.kotlin_demoapp.tagb.module.dashboard.viewModel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kotlin_demoapp.tagb.base_classes.BaseViewModel
import com.example.kotlin_demoapp.tagb.module.dashboard.model.BasePageResponse
import com.example.kotlin_demoapp.tagb.module.dashboard.model.response.MovieInfo
import com.example.kotlin_demoapp.tagb.module.dashboard.repository.MovieRepository
import com.example.kotlin_demoapp.tagb.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: MovieRepository
) : BaseViewModel() {

    private val _fetchListSuccess = MutableLiveData<List<MovieInfo>>()
    val fetchListSuccess: LiveData<List<MovieInfo>>
        get() = _fetchListSuccess
    private val _fetchListFailure = MutableLiveData<String>()
    val fetchListFailure: LiveData<String>
        get() = _fetchListFailure

    @SuppressLint("NullSafeMutableLiveData")
    fun getMovieList(accessToken: String, page: Int) {
        viewModelScope.launch {
            repository.getMovieData(accessToken, page)
                .collect {
                _fetchListSuccess.postValue(it.result)
            }
        }
    }
}