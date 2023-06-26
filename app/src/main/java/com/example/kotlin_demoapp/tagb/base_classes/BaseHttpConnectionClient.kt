package com.example.kotlin_demoapp.tagb.base_classes

import com.example.kotlin_demoapp.tagb.helper.HttpRequestType
import com.example.kotlin_demoapp.tagb.helper.stringToModel
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

object BaseHttpConnectionClient {

    inline fun <reified T> request(
        type: HttpRequestType,
        url: String,
        header: HashMap<String, String>?,
        bodyParams: HashMap<String, Any>?,
        crossinline success: (T) -> Unit,
        crossinline failure: (responseCode: Int, responseMessage: String) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            // Open connection
            val connection: HttpURLConnection = URL(url).openConnection() as HttpURLConnection
            // Sending Part
            header?.forEach { (key, value) ->
                connection.setRequestProperty(key, value)
            }
            connection.setRequestProperty("Content-Type", "application/json")
            connection.setRequestProperty("Accept", "application/json")
            connection.requestMethod = type.name
            // Writing Request Body
            when (type) {
                HttpRequestType.PATCH, HttpRequestType.POST, HttpRequestType.PUT -> {
                    connection.doOutput = true
                    val os = DataOutputStream(connection.outputStream)
                    os.apply {
                        writeBytes(Gson().toJson(bodyParams))
                        flush()
                        close()
                    }
                }

                else -> {}
            }
            connection.connect()
            // Receiving Part
            when (connection.responseCode) {
                HttpURLConnection.HTTP_OK, HttpURLConnection.HTTP_CREATED -> {
                    //read data from response
                    val `in` = BufferedReader(InputStreamReader(connection.inputStream))
                    val sb = StringBuffer("")
                    var line: String? = ""
                    if (`in`.readLine().also { line = it } != null) {
                        sb.append(line)
                    }
                    `in`.close()
                    val model = stringToModel(sb.toString(), T::class.java)
                    //send response back to Main thread
                    withContext(Dispatchers.Main) {
                        success(model)
                    }
                }

                else -> {
                    //send response back to Main thread
                    withContext(Dispatchers.Main) {
                        failure(connection.responseCode, connection.responseMessage)
                    }
                }
            }
        }
    }
}

