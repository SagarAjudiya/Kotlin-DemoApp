package com.example.kotlin_demoapp.tagb.api_services

import com.example.kotlin_demoapp.tagb.helper.Endpoints
import com.example.kotlin_demoapp.tagb.module.dashboard.model.response.EmployeeInfo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface EmployeeService {

    @GET(Endpoints.EMPLOYEE)
    suspend fun getEmployee(): List<EmployeeInfo>

    @GET(Endpoints.EMPLOYEE)
    suspend fun getEmployeeWithSearch(
        @Query("search") search: String
    ): List<EmployeeInfo>

    @POST(Endpoints.EMPLOYEE)
    suspend fun addEmployee(
        @Body body: HashMap<String, Any>
    ): EmployeeInfo

    @PATCH(Endpoints.EMPLOYEE_WITH_ID)
    suspend fun updateEmployee(
        @Body body: HashMap<String, Any>,
        @Path("id") id: String
    ): EmployeeInfo

    @DELETE(Endpoints.EMPLOYEE_WITH_ID)
    suspend fun deleteEmployee(
        @Path("id") id: String
    ): EmployeeInfo
}