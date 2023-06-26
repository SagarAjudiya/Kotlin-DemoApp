package com.example.kotlin_demoapp.architecture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.databinding.ActivityMainViewModelBinding

class ViewModelActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainViewModelBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainViewModelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mainViewModelProvider = MainViewModelFactory("Ruchit Kalathiya")
        viewModel =  ViewModelProvider(this, mainViewModelProvider)[MainViewModel::class.java]
        binding.btnStart.setOnClickListener(this)
        binding.btnStop.setOnClickListener(this)

        viewModel.second.observe(this, Observer {
            binding.txtTimer.text = it.toString()
        })

        viewModel.finishTimer = {
            Toast.makeText(this, "Finished!!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            binding.btnStart.id -> {
                if (binding.etTimer.text.toString().isNotEmpty()) {
                    viewModel.timerValue = binding.etTimer.text.toString().toInt()
                    viewModel.startTimer()
                } else {
                    Toast.makeText(this, "Invalid Number", Toast.LENGTH_SHORT).show()
                }
            }

            binding.btnStop.id -> {
                binding.txtTimer.text = "0"
                viewModel.stopTimer()
            }
        }
    }
}