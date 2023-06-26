package com.example.kotlin_demoapp.tagb.repository

import com.example.kotlin_demoapp.tagb.api.RetrofitInstance
import com.example.kotlin_demoapp.tagb.helper.Constants
import com.example.kotlin_demoapp.tagb.module.authentication.model.BaseResponseModel
import com.example.kotlin_demoapp.tagb.module.authentication.model.request.LoginRequestModel
import com.example.kotlin_demoapp.tagb.module.authentication.model.request.SignUpRequestModel
import com.example.kotlin_demoapp.tagb.module.authentication.model.response.LoginResponseModel
import com.example.kotlin_demoapp.tagb.module.dashboard.model.BasePageResponse
import com.example.kotlin_demoapp.tagb.module.dashboard.model.BaseUserResponse
import com.example.kotlin_demoapp.tagb.module.dashboard.model.response.EmployeeInfo
import com.example.kotlin_demoapp.tagb.module.dashboard.model.response.ImageResponse
import com.example.kotlin_demoapp.tagb.module.dashboard.model.response.MovieInfo
import okhttp3.MultipartBody
import retrofit2.Call

object Repository {

    fun loginUser(body: LoginRequestModel): Call<BaseResponseModel<LoginResponseModel>> {
        return RetrofitInstance(Constants.AUTH_BASE_URL).api.loginUser(body)
    }

    fun signUpUser(body: SignUpRequestModel): Call<BaseResponseModel<LoginResponseModel>> {
        return RetrofitInstance(Constants.AUTH_BASE_URL).api.signUpUser(body)
    }

    fun getUser(): Call<BaseUserResponse> {
        return RetrofitInstance(Constants.BASE_URL).api.getUser()
    }

    fun getMovieData(
        accessToken: String,
        pageNumber: Int
    ): Call<BasePageResponse<List<MovieInfo>>> {
        return RetrofitInstance(Constants.MOVIE_BASE_URL).api.getMovie(accessToken, pageNumber)
    }

    fun getEmployee(): Call<List<EmployeeInfo>> {
        return RetrofitInstance(Constants.EMPLOYEE_BASE_URL).api.getEmployee()
    }

    fun getEmployeeWithSearch(search: String): Call<List<EmployeeInfo>> {
        return RetrofitInstance(Constants.EMPLOYEE_BASE_URL).api.getEmployeeWithSearch(search)
    }

    fun addEmployee(body: HashMap<String, Any>): Call<EmployeeInfo> {
        return RetrofitInstance(Constants.EMPLOYEE_BASE_URL).api.addEmployee(body)
    }

    fun updateEmployee(body: HashMap<String, Any>, id: String): Call<EmployeeInfo> {
        return RetrofitInstance(Constants.EMPLOYEE_BASE_URL).api.updateEmployee(body, id)
    }

    fun deleteEmployee(id: String): Call<EmployeeInfo> {
        return RetrofitInstance(Constants.EMPLOYEE_BASE_URL).api.deleteEmployee(id)
    }

    fun uploadImage(accessToken: String, part: MultipartBody.Part): Call<ImageResponse> {
        return RetrofitInstance(Constants.IMAGE_BASE_URL).api.uploadImage(accessToken, part)
    }
}