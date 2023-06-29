package com.example.kotlin_demoapp.tagb.module.dashboard.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kotlin_demoapp.tagb.base_classes.BaseViewModel
import com.example.kotlin_demoapp.tagb.module.dashboard.model.response.PersonAPI
import com.example.kotlin_demoapp.tagb.module.dashboard.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UserRepository
) : BaseViewModel() {

    private val _responseData = MutableLiveData<List<PersonAPI>>()
    val responseData: LiveData<List<PersonAPI>>
        get() = _responseData

    fun getUser() {
        viewModelScope.launch {
            repository.getUser().collect {
                _responseData.postValue(it.data)
            }
        }
    }
}