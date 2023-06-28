package com.example.kotlin_demoapp.tagb.helper

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Looper
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun getURL(baseURL: String, version: String, path: String): String = baseURL + version + path

/**
 * change UTC timing to local timing
 */
fun utcToLocal(utcTime: String): String {
    val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault()).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }.parse(utcTime) ?: return ""
    return SimpleDateFormat("MMM dd, yyyy HH:mm:ss a", Locale.getDefault()).apply {
        timeZone = TimeZone.getTimeZone("GMT")
    }.format(date)
}

/**
 * change date to UTC timing
 */
fun dateToUTC(date: Date): String {
    return SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault()).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }.format(date)
}

/**
 * get current timing
 */
fun getCurrentTime(): String {
    return SimpleDateFormat("yyyy-MM-dd'_'HH:mm:ss", Locale.getDefault()).apply {
        timeZone = TimeZone.getTimeZone("GMT")
    }.format(Calendar.getInstance().time)
}

/**
 * change json string to model
 */
fun <T> stringToModel(jsonString: String, model: Class<T>): T = Gson().fromJson(jsonString, model)

fun getCameraPermission(activity: Activity): Boolean {
    if (ContextCompat.checkSelfPermission(
            activity, Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_DENIED
    ) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                activity,
                Manifest.permission.CAMERA
            )
        ) {
            AlertDialog.Builder(activity)
                .setTitle("Permission Needed")
                .setMessage("This permission is needed to access camera")
                .setPositiveButton("ok") { _, _ ->
                    ActivityCompat.requestPermissions(
                        activity,
                        arrayOf(Manifest.permission.CAMERA),
                        100
                    )
                }
                .setNegativeButton("Cancel", null)
                .create().show()
        } else {
            ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.CAMERA), 100)
        }
        return false
    }
    return true
}

fun getStoragePermission(activity: Activity, errorMessage: String): Boolean {
    if (ContextCompat.checkSelfPermission(
            activity, Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_DENIED
    ) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                activity,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        ) {
            AlertDialog.Builder(activity)
                .setTitle("Permission Needed")
                .setMessage("This permission is needed to access camera")
                .setPositiveButton("ok") { _, _ ->
                    ActivityCompat.requestPermissions(
                        activity,
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                        100
                    )
                }
                .setOnCancelListener {
                    Toast.makeText(activity, errorMessage, Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Cancel") { _, _ ->
                    Toast.makeText(activity, errorMessage, Toast.LENGTH_SHORT).show()
                }
                .create().show()
        } else {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                100
            )
        }
        return false
    }
    return true
}

fun putDelay(delayMillis: Long, completion: (() -> Unit)?) {
    android.os.Handler(Looper.getMainLooper()).postDelayed({
        if (completion != null) {
            completion()
        }
    }, delayMillis)
}