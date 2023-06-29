package com.example.kotlin_demoapp.tagb.module.dashboard.repository

import com.example.kotlin_demoapp.tagb.api_services.EmployeeService
import com.example.kotlin_demoapp.tagb.api_services.ImageService
import com.example.kotlin_demoapp.tagb.helper.UserDefault
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MultipartBody
import javax.inject.Inject

class EmployeeRepository @Inject constructor(
    private val employeeService: EmployeeService,
    private val imageService: ImageService
) {
    suspend fun getEmployee() = flow {
        emit(employeeService.getEmployee())
    }.flowOn(Dispatchers.IO)

    suspend fun getEmployeeWithSearch(search: String) = flow {
        emit(employeeService.getEmployeeWithSearch(search))
    }.flowOn(Dispatchers.IO)

    suspend fun addEmployee(body: HashMap<String, Any>) = flow {
        emit(employeeService.addEmployee(body))
    }.flowOn(Dispatchers.IO)

    suspend fun updateEmployee(body: HashMap<String, Any>, id: String) = flow {
        emit(employeeService.updateEmployee(body, id))
    }.flowOn(Dispatchers.IO)

    suspend fun deleteEmployee(id: String) = flow {
        emit(employeeService.deleteEmployee(id))
    }.flowOn(Dispatchers.IO)

    suspend fun uploadImage(part: MultipartBody.Part) = flow {
        emit(imageService.uploadImage(UserDefault.IMAGE_ACCESS_TOKEN, part))
    }.flowOn(Dispatchers.IO)
}