package com.example.kotlin_demoapp.tagb.module.dashboard.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.kotlin_demoapp.tagb.base_classes.BaseViewModel
import com.example.kotlin_demoapp.tagb.module.dashboard.model.BaseUserResponse
import com.example.kotlin_demoapp.tagb.module.dashboard.model.response.PersonAPI
import com.example.kotlin_demoapp.tagb.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel() : BaseViewModel() {

    val responseData: MutableLiveData<List<PersonAPI>> = MutableLiveData()

    fun getUser() {
        Repository.getUser().enqueue(object : Callback<BaseUserResponse> {
            override fun onResponse(
                call: Call<BaseUserResponse>?,
                response: Response<BaseUserResponse>?
            ) {
                if (response != null && response.isSuccessful) {
                    responseData.value = response.body().data
                    Log.d("API", "API Success")
                }
            }

            override fun onFailure(call: Call<BaseUserResponse>?, t: Throwable?) {
                Log.d("API", "API Failure: " + t.toString())
            }
        })
    }
}