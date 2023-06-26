package com.example.kotlin_demoapp.tagb.helper

import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.provider.OpenableColumns
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import com.example.kotlin_demoapp.tagb.utils.App

// Activity Functions
/**
 * Make window touchable and untouchable
 */
fun Activity.setWindowTouchable(isTouchable: Boolean) {
    if (isTouchable) {
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    } else {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        );
    }
}

inline fun <reified T : AppCompatActivity> Activity.launchActivity() {
    val intent = Intent(this, T::class.java)
    startActivity(intent)
}

inline fun <reified T : AppCompatActivity> Activity.launchActivityWithClearTask() {
    val intent = Intent(this, T::class.java)
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivity(intent)
}

/**
 * change statusBar text appearance
 */
fun Activity.setStatusBarLightAppearance(isDark: Boolean) {
    WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars =
        isDark
}

// Fragment Functions
/**
 * Make window touchable and untouchable
 */
fun Fragment.setWindowTouchable(isTouchable: Boolean) {
    if (isTouchable) {
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    } else {
        activity?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        );
    }
}

//ContentResolver Functions
/**
 * Get File name of given uri
 * Get the column indexes of the data in the Cursor,
 * move to the first row in the Cursor, get the data,
 * and display it.
 */
fun ContentResolver.getFileName(uri: Uri): String {
    var name = ""
    val cursor = query(uri, null, null, null, null)
    cursor?.let {
        val index = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        it.moveToFirst()
        name = it.getString(index)
        it.close()
    }
    return name
}

//Drawable Function
/**
 * change drawable color
 */
fun Drawable.setDrawableColor(color: Int) {
    this.colorFilter =
        BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
            ContextCompat.getColor(App.appContext, color),
            BlendModeCompat.SRC_ATOP
        )
}

//String Function
/**
 * validate email
 */
val String.isValidEmail: Boolean
    get() = android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches();
