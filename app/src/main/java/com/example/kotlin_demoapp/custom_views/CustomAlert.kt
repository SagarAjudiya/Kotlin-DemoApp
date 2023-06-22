package com.example.kotlin_demoapp.custom_views

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.view.Window
import android.widget.Button
import android.widget.TextView
import com.example.kotlin_demoapp.R

class CustomAlert(
    private val context: Context,
    private val title: String,
    private val msg: String,
    private val completion: () -> Unit
) : Dialog(context), OnClickListener {

    private lateinit var cancel: Button
    private lateinit var confirm: Button
    private lateinit var txtTitle: TextView
    private lateinit var txtMsg: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.alert_dialog)
        cancel = findViewById(R.id.btnCancel)
        confirm = findViewById(R.id.btnSignUp)
        txtTitle = findViewById(R.id.txtConfirmSignUp)
        txtMsg = findViewById(R.id.txtConfirmDetails)
        txtTitle.text = title
        txtMsg.text = msg
        cancel.setOnClickListener(this)
        confirm.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            cancel.id -> dismiss()
            confirm.id -> {
                completion()
                dismiss()
            }
        }
    }
}