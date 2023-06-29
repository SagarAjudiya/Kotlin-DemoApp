package com.example.kotlin_demoapp.tagb.module.dashboard.viewModel

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.example.kotlin_demoapp.tagb.base_classes.BaseHttpConnectionClient
import com.example.kotlin_demoapp.tagb.base_classes.BaseViewModel
import com.example.kotlin_demoapp.tagb.base_classes.Event
import com.example.kotlin_demoapp.tagb.helper.Constants
import com.example.kotlin_demoapp.tagb.helper.HttpRequestType
import com.example.kotlin_demoapp.tagb.helper.UploadRequestBody
import com.example.kotlin_demoapp.tagb.helper.dateToUTC
import com.example.kotlin_demoapp.tagb.helper.getFileName
import com.example.kotlin_demoapp.tagb.module.dashboard.model.response.EmployeeInfo
import com.example.kotlin_demoapp.tagb.module.dashboard.repository.EmployeeRepository
import com.example.kotlin_demoapp.tagb.utils.App
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.FileDescriptor
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.net.URL
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class EmployeeViewModel @Inject constructor(
    private val repository: EmployeeRepository
) : BaseViewModel() {

    private var _fetchEmployeeListSuccess = MutableLiveData<Event<List<EmployeeInfo>>>()
    val fetchEmployeeListSuccess: LiveData<Event<List<EmployeeInfo>>>
        get() = _fetchEmployeeListSuccess
    var fetchEmployeeListFailure: MutableLiveData<Event<String>> = MutableLiveData()
    private var _updateEmployeeSuccess = MutableLiveData<Event<Pair<Int, EmployeeInfo>>>()
    val updateEmployeeSuccess: LiveData<Event<Pair<Int, EmployeeInfo>>>
        get() = _updateEmployeeSuccess
    var updateEmployeeFailure: MutableLiveData<Event<String>> = MutableLiveData()
    private var _viewEmployeeSuccess = MutableLiveData<Event<EmployeeInfo>>()
    val viewEmployeeSuccess: LiveData<Event<EmployeeInfo>>
        get() = _viewEmployeeSuccess
    var viewEmployeeFailure: MutableLiveData<Event<String>> = MutableLiveData()
    private var _addEmployeeSuccess = MutableLiveData<Event<EmployeeInfo>>()
    val addEmployeeSuccess: LiveData<Event<EmployeeInfo>>
        get() = _addEmployeeSuccess
    var addEmployeeFailure: MutableLiveData<Event<String>> = MutableLiveData()
    private var _deleteEmployeeSuccess = MutableLiveData<Event<Int>>()
    val deleteEmployeeSuccess: LiveData<Event<Int>>
        get() = _deleteEmployeeSuccess
    var deleteEmployeeFailure: MutableLiveData<Event<String>> = MutableLiveData()
    var uploadImageFailure: MutableLiveData<Event<String>> = MutableLiveData()

    /**
     * Fetch Employee list with Retrofit
     */
    fun getEmployeeList(search: String = "") {
        viewModelScope.launch {
            if (search.isEmpty()) {
                repository.getEmployee().collect {
                    _fetchEmployeeListSuccess.postValue(Event(it))
                }
            } else {
                repository.getEmployeeWithSearch(search).collect {
                    _fetchEmployeeListSuccess.postValue(Event(it))
                }
            }
        }
    }

    @SuppressLint("Recycle")
    fun uploadImageAndUpdate(image: Uri, name: String, position: Int, id: String) {
        val parcelDescription =
            App.appContext.contentResolver?.openFileDescriptor(image, "r", null)
        val inputStream = FileInputStream(parcelDescription?.fileDescriptor ?: FileDescriptor())
        val file = File(
            App.appContext.cacheDir,
            App.appContext.contentResolver.getFileName(image)
        )
        val outputStream = FileOutputStream(file)
        inputStream.copyTo(outputStream)
        val body = UploadRequestBody(file, "multipart/form-data")
        //add API call
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                uploadImageAndUpdate(
                    part = MultipartBody.Part.createFormData(
                        "file",
                        file.name,
                        body
                    ), name, position, id
                )
            }
        }
    }

    @SuppressLint("Recycle")
    fun uploadImageAndAdd(image: Uri, name: String) {
        val parcelDescription =
            App.appContext.contentResolver?.openFileDescriptor(image, "r", null)
        val inputStream = FileInputStream(parcelDescription?.fileDescriptor ?: FileDescriptor())
        val file = File(
            App.appContext.cacheDir,
            App.appContext.contentResolver.getFileName(image)
        )
        val outputStream = FileOutputStream(file)
        inputStream.copyTo(outputStream)
        val body = UploadRequestBody(file, "multipart/form-data")
        //add API call
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                uploadImageAndAdd(
                    part = MultipartBody.Part.createFormData(
                        "file",
                        file.name,
                        body
                    ), name = name
                )
            }
        }
    }

    /**
     * Add Employee details with Retrofit
     */
    fun addEmployee(body: HashMap<String, Any>) {

        viewModelScope.launch {
            repository.addEmployee(body).collect {
                _addEmployeeSuccess.postValue(Event(it))
            }
        }
    }

    /**
     * Add Employee details [Practice API call with HttpURLConnection]
     */
    fun addEmployeeWithHttp(body: HashMap<String, Any>) {
        val url = Constants.EMPLOYEE_BASE_URL + "employee"
        BaseHttpConnectionClient.request<EmployeeInfo>(
            HttpRequestType.POST,
            url,
            null,
            body,
            { employee ->
                _addEmployeeSuccess.value = Event(employee)
            }) { responseCode, responseMessage ->
            Log.d("API", "$responseCode : $responseMessage")
            addEmployeeFailure.value = Event(responseMessage)
        }
    }

    /**
     * Update Employee details with Retrofit
     */
    fun updateEmployee(body: HashMap<String, Any>, position: Int, id: String) {
        viewModelScope.launch {
            repository.updateEmployee(body, id).collect {
                _updateEmployeeSuccess.postValue(Event(Pair(position, it)))
            }
        }
    }

    /**
     * Update Employee details [Practice API call with HttpURLConnection]
     */
    fun updateEmployeeWithHttp(body: HashMap<String, Any>, position: Int, id: String) {
        val url = Constants.EMPLOYEE_BASE_URL + "employee/" + id
        BaseHttpConnectionClient.request<EmployeeInfo>(
            HttpRequestType.PATCH,
            url,
            null,
            body,
            { employee ->
                _updateEmployeeSuccess.value = Event(Pair(position, employee))
            }) { responseCode, responseMessage ->
            Log.d("API", "$responseCode : $responseMessage")
            updateEmployeeFailure.value = Event(responseMessage)
        }
    }

    /**
     * Delete Employee details with Retrofit
     */
    fun deleteEmployee(id: String, position: Int) {
        viewModelScope.launch {
            repository.deleteEmployee(id).collect {
                _deleteEmployeeSuccess.postValue(Event(position))
            }
        }
    }

    /**
     * Delete Employee details [Practice API call with HttpURLConnection]
     */
    fun deleteEmployeeWithHttp(id: String, position: Int) {
        val url = Constants.EMPLOYEE_BASE_URL + "employee/" + id
        BaseHttpConnectionClient.request<EmployeeInfo>(
            HttpRequestType.DELETE,
            url,
            null,
            null,
            { employee ->
                _deleteEmployeeSuccess.value = Event(position)
            }) { responseCode, responseMessage ->
            Log.d("API", "$responseCode : $responseMessage")
            deleteEmployeeFailure.value = Event(responseMessage)
        }
    }

    /**
     * Show Employee details [Practice API call with HttpURLConnection]
     */
    fun viewEmployee(id: String) {
        val url = Constants.EMPLOYEE_BASE_URL + "employee/" + id
        BaseHttpConnectionClient.request<EmployeeInfo>(
            HttpRequestType.GET,
            url,
            null,
            null,
            { employee ->
                _viewEmployeeSuccess.value = Event(employee)
            }) { responseCode, responseMessage ->
            Log.d("API", "$responseCode : $responseMessage")
            viewEmployeeFailure.value = Event(responseMessage)
        }
    }

    /**
     * Show Employee details [Practice API call with OkHttp3]
     */
    fun viewEmployeeWithOkHttp(id: String) {
        val url = URL(Constants.EMPLOYEE_BASE_URL + "employee/" + id)
        val request = Request.Builder()
            .url(url)
            .get()
            .build()
        OkHttpClient().newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                call.cancel()
                e.localizedMessage?.let { viewEmployeeFailure.value = Event(it) }
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                val body = response.body
                if (response.isSuccessful && body != null) {
                    //String to Model with Gson
                    val model = Gson().fromJson(body.string(), EmployeeInfo::class.java)
                    //update UI
                    viewModelScope.launch {
                        withContext(Dispatchers.Main) {
                            _viewEmployeeSuccess.value = Event(model)
                        }
                    }
                } else {
                    viewEmployeeFailure.value = Event(response.message)
                }
            }
        })
    }

    /**
     * Show Employee details [Practice API call with Volley]
     */
    fun viewEmployeeWithVolley(queue: RequestQueue, id: String) {
        val url = Constants.EMPLOYEE_BASE_URL + "employee/" + id
        val request = JsonObjectRequest(com.android.volley.Request.Method.GET, url, null,
            { response ->
                viewModelScope.launch {
                    withContext(Dispatchers.Main) {
                        _viewEmployeeSuccess.value =
                            Event(Gson().fromJson(response.toString(), EmployeeInfo::class.java))
                    }
                }
            }
        ) { error ->
            error.message?.let { viewEmployeeFailure.value = Event(it) }
        }

        queue.add(request)
    }

    /**
     * Upload Employee details with Image with Retrofit
     */
    private fun uploadImageAndAdd(part: MultipartBody.Part, name: String) {
        viewModelScope.launch {
            repository.uploadImage(part).collect {
                addEmployee(
                    hashMapOf(
                        "id" to "null",
                        "name" to name,
                        "createdAt" to dateToUTC(Calendar.getInstance().time),
                        "avatar" to it.imageFiles[0].fileUrl
                    )
                )
            }
        }
    }

    private fun uploadImageAndUpdate(
        part: MultipartBody.Part,
        name: String,
        position: Int,
        id: String
    ) {
        viewModelScope.launch {
            repository.uploadImage(part)
                .catch {
                    uploadImageFailure.postValue(Event(it.localizedMessage))
                }
                .collect {
                updateEmployee(
                    hashMapOf(
                        "id" to "null",
                        "name" to name,
                        "createdAt" to dateToUTC(Calendar.getInstance().time),
                        "avatar" to it.imageFiles[0].fileUrl
                    ),
                    position,
                    id
                )
            }
        }
    }
}