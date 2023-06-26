package com.example.kotlin_demoapp.tagb.module.dashboard.viewModel

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
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
import com.example.kotlin_demoapp.tagb.helper.UserDefault
import com.example.kotlin_demoapp.tagb.helper.dateToUTC
import com.example.kotlin_demoapp.tagb.helper.getFileName
import com.example.kotlin_demoapp.tagb.module.dashboard.model.response.EmployeeInfo
import com.example.kotlin_demoapp.tagb.module.dashboard.model.response.ImageResponse
import com.example.kotlin_demoapp.tagb.repository.Repository
import com.example.kotlin_demoapp.tagb.utils.App
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileDescriptor
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.net.URL
import java.util.Calendar

class EmployeeViewModel() : BaseViewModel() {

    val fetchEmployeeListSuccess: MutableLiveData<Event<List<EmployeeInfo>>> = MutableLiveData()
    var fetchEmployeeListFailure: MutableLiveData<Event<String>> = MutableLiveData()
    val updateEmployeeSuccess: MutableLiveData<Event<Pair<Int, EmployeeInfo>>> = MutableLiveData()
    var updateEmployeeFailure: MutableLiveData<Event<String>> = MutableLiveData()
    val viewEmployeeSuccess: MutableLiveData<Event<EmployeeInfo>> = MutableLiveData()
    var viewEmployeeFailure: MutableLiveData<Event<String>> = MutableLiveData()
    var addEmployeeSuccess: MutableLiveData<Event<EmployeeInfo>> = MutableLiveData()
    var addEmployeeFailure: MutableLiveData<Event<String>> = MutableLiveData()
    var deleteEmployeeSuccess: MutableLiveData<Event<Int>> = MutableLiveData()
    var deleteEmployeeFailure: MutableLiveData<Event<String>> = MutableLiveData()
    var uploadImageFailure: MutableLiveData<Event<String>> = MutableLiveData()

    /**
     * Fetch Employee list with Retrofit
     */
    fun getEmployeeList(search: String = "") {
        if (search.isEmpty()) {
            Repository.getEmployee().enqueue(object : Callback<List<EmployeeInfo>> {
                override fun onResponse(
                    call: Call<List<EmployeeInfo>>?,
                    response: Response<List<EmployeeInfo>>?
                ) {
                    if (response != null && response.isSuccessful) {
                        fetchEmployeeListSuccess.value = Event(response.body().toMutableList())
                    } else {
                        fetchEmployeeListFailure.value = Event(response.toString())
                    }
                }

                override fun onFailure(call: Call<List<EmployeeInfo>>?, t: Throwable?) {
                    t?.localizedMessage?.let { fetchEmployeeListFailure.value = Event(it) }
                }
            })
        } else {
            Repository.getEmployeeWithSearch(search).enqueue(object : Callback<List<EmployeeInfo>> {
                override fun onResponse(
                    call: Call<List<EmployeeInfo>>?,
                    response: Response<List<EmployeeInfo>>?
                ) {
                    if (response != null && response.isSuccessful) {
                        fetchEmployeeListSuccess.value = Event(response.body().toMutableList())
                    } else {
                        fetchEmployeeListFailure.value = Event(response.toString())
                    }
                }

                override fun onFailure(call: Call<List<EmployeeInfo>>?, t: Throwable?) {
                    t?.localizedMessage?.let { fetchEmployeeListFailure.value = Event(it) }
                }
            })
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
        Repository.addEmployee(body).enqueue(object : Callback<EmployeeInfo> {
            override fun onResponse(
                call: Call<EmployeeInfo>?,
                response: Response<EmployeeInfo>?
            ) {
                if (response != null && response.isSuccessful) {
                    addEmployeeSuccess.value = Event(response.body())
                } else {
                    addEmployeeFailure.value = Event(response.toString())
                }
            }

            override fun onFailure(call: Call<EmployeeInfo>?, t: Throwable?) {
                t?.localizedMessage?.let { addEmployeeFailure.value = Event(it) }
            }
        })
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
                addEmployeeSuccess.value = Event(employee)
            }) { responseCode, responseMessage ->
            Log.d("API", "$responseCode : $responseMessage")
            addEmployeeFailure.value = Event(responseMessage)
        }
    }

    /**
     * Update Employee details with Retrofit
     */
    fun updateEmployee(body: HashMap<String, Any>, position: Int, id: String) {
        Repository.updateEmployee(body, id).enqueue(object : Callback<EmployeeInfo> {
            override fun onResponse(call: Call<EmployeeInfo>?, response: Response<EmployeeInfo>?) {
                if (response != null && response.isSuccessful) {
                    updateEmployeeSuccess.value = Event(Pair(position, response.body()))
                } else {
                    updateEmployeeFailure.value = Event(response.toString())
                }
            }

            override fun onFailure(call: Call<EmployeeInfo>?, t: Throwable?) {
                t?.localizedMessage?.let { updateEmployeeFailure.value = Event(it) }
            }
        })
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
                updateEmployeeSuccess.value = Event(Pair(position, employee))
            }) { responseCode, responseMessage ->
            Log.d("API", "$responseCode : $responseMessage")
            updateEmployeeFailure.value = Event(responseMessage)
        }
    }

    /**
     * Delete Employee details with Retrofit
     */
    fun deleteEmployee(id: String, position: Int) {
        Repository.deleteEmployee(id).enqueue(object : Callback<EmployeeInfo> {
            override fun onResponse(call: Call<EmployeeInfo>?, response: Response<EmployeeInfo>?) {
                if (response != null && response.isSuccessful) {
                    deleteEmployeeSuccess.value = Event(position)
                } else {
                    deleteEmployeeFailure.value = Event(response.toString())
                }
            }

            override fun onFailure(call: Call<EmployeeInfo>?, t: Throwable?) {
                t?.localizedMessage?.let { deleteEmployeeFailure.value = Event(it) }
            }
        })
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
                deleteEmployeeSuccess.value = Event(position)
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
                viewEmployeeSuccess.value = Event(employee)
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
                            viewEmployeeSuccess.value = Event(model)
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
                        viewEmployeeSuccess.value =
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
    private fun uploadImageAndAdd(part: MultipartBody.Part, name: String): String {
        var resultURL = "null"
        Repository.uploadImage(UserDefault.IMAGE_ACCESS_TOKEN, part)
            .enqueue(object : Callback<ImageResponse> {
                override fun onResponse(
                    call: Call<ImageResponse>?,
                    response: Response<ImageResponse>?
                ) {
                    if (response != null && response.isSuccessful) {
                        Log.d(
                            "API",
                            "onResponse IMAGE SUCCESS : " + response.body().imageFiles[0].fileUrl
                        )
                        resultURL = response.body().imageFiles[0].fileUrl
                        // if uploaded successfully call add employee
                        addEmployee(
                            hashMapOf(
                                "id" to "null",
                                "name" to name,
                                "createdAt" to dateToUTC(Calendar.getInstance().time),
                                "avatar" to resultURL
                            )
                        )
                    } else {
                        response?.let { uploadImageFailure.value = Event(it.message()) }
                    }
                }

                override fun onFailure(call: Call<ImageResponse>?, t: Throwable?) {
                    t?.let { uploadImageFailure.value = Event(it.localizedMessage) }
                }
            })
        return resultURL
    }

    private fun uploadImageAndUpdate(
        part: MultipartBody.Part,
        name: String,
        position: Int,
        id: String
    ): String {
        var resultURL = "null"
        Repository.uploadImage(UserDefault.IMAGE_ACCESS_TOKEN, part)
            .enqueue(object : Callback<ImageResponse> {
                override fun onResponse(
                    call: Call<ImageResponse>?,
                    response: Response<ImageResponse>?
                ) {
                    if (response != null && response.isSuccessful) {
                        Log.d(
                            "API",
                            "onResponse IMAGE SUCCESS : " + response.body().imageFiles[0].fileUrl
                        )
                        resultURL = response.body().imageFiles[0].fileUrl
                        // if uploaded successfully call add employee
                        updateEmployee(
                            hashMapOf(
                                "id" to "null",
                                "name" to name,
                                "createdAt" to dateToUTC(Calendar.getInstance().time),
                                "avatar" to resultURL
                            ),
                            position,
                            id
                        )
                    } else {
                        response?.let { uploadImageFailure.value = Event(it.message()) }
                    }
                }

                override fun onFailure(call: Call<ImageResponse>?, t: Throwable?) {
                    t?.let { uploadImageFailure.value = Event(it.localizedMessage) }
                }
            })
        return resultURL
    }
}