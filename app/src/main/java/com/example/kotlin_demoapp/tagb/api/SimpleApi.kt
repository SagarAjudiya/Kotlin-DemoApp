package com.example.kotlin_demoapp.tagb.api

import com.example.kotlin_demoapp.tagb.helper.Endpoints
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
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface SimpleApi {

    @POST(Endpoints.LOGIN)
    fun loginUser(
        @Body body: LoginRequestModel
    ): Call<BaseResponseModel<LoginResponseModel>>

    @POST(Endpoints.SIGNUP)
    fun signUpUser(
        @Body body: SignUpRequestModel
    ): Call<BaseResponseModel<LoginResponseModel>>

    @GET(Endpoints.GET_USER)
    fun getUser(): Call<BaseUserResponse>

    @GET(Endpoints.GET_MOVIES)
    fun getMovie(
        @Header("Authorization") accessToken: String,
        @Query("page") page: Int,
        @Query("language") language: String = "en-US"
    ): Call<BasePageResponse<List<MovieInfo>>>

    @GET(Endpoints.EMPLOYEE)
    fun getEmployee(): Call<List<EmployeeInfo>>

    @GET(Endpoints.EMPLOYEE)
    fun getEmployeeWithSearch(
        @Query("search") search: String
    ): Call<List<EmployeeInfo>>

    @POST(Endpoints.EMPLOYEE)
    fun addEmployee(
        @Body body: HashMap<String, Any>
    ): Call<EmployeeInfo>

    @PATCH(Endpoints.EMPLOYEE_WITH_ID)
    fun updateEmployee(
        @Body body: HashMap<String, Any>,
        @Path("id") id: String
    ): Call<EmployeeInfo>

    @DELETE(Endpoints.EMPLOYEE_WITH_ID)
    fun deleteEmployee(
        @Path("id") id: String
    ): Call<EmployeeInfo>

    @Multipart
    @POST(Endpoints.FORM_DATA)
    fun uploadImage(
        @Header("Authorization") accessToken: String,
        @Part image: MultipartBody.Part
    ): Call<ImageResponse>
}