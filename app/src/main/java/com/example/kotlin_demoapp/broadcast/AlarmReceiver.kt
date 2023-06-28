package com.example.kotlin_demoapp.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.kotlin_demoapp.activity.MainActivity
import com.example.kotlin_demoapp.tagb.module.dashboard.view.WebServiceActivity

class AlarmReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.startActivity(Intent(context, WebServiceActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            putExtra("from_notification",true)
        })
        Toast.makeText(context,"Pending Intent Triggered",Toast.LENGTH_SHORT).show()
    }
}