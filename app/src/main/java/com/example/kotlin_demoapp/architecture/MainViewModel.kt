package com.example.kotlin_demoapp.architecture

import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel(private val name: String): ViewModel() {

    private lateinit var timer: CountDownTimer
    private var currentSecond = MutableLiveData<Int>()
    val second: LiveData<Int>
        get() = currentSecond
    var timerValue = 10
    var finishTimer:  (() -> Unit) = {}

    fun getArgument() {
        Log.d("TAG", "getString: $name")
    }

    fun startTimer() {
        timer = object: CountDownTimer((timerValue * 1000).toLong(),1000) {
            override fun onTick(timeLeft: Long) {
                Log.d("TAG", "Tick: ${timeLeft / 1000}")
                currentSecond.value = (timeLeft / 1000).toInt()
            }

            override fun onFinish() {
                Log.d("TAG", "Finished!!")
                finishTimer()
            }
        }.start()
    }

    fun stopTimer() {
        timer.cancel()
    }
}