package com.example.kotlin_demoapp.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.kotlin_demoapp.activity.intent.ImplicitIntent

class AlarmReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.startActivity(Intent(context,ImplicitIntent::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        })
        Toast.makeText(context,"Pending Intent Triggered",Toast.LENGTH_SHORT).show()
    }
}