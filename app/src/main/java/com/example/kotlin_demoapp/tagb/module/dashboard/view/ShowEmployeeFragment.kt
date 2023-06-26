package com.example.kotlin_demoapp.tagb.module.dashboard.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.databinding.FragmentShowEmployeeBinding
import com.example.kotlin_demoapp.tagb.helper.utcToLocal
import com.example.kotlin_demoapp.tagb.module.dashboard.model.response.EmployeeInfo

class ShowEmployeeFragment(private val employee: EmployeeInfo) : DialogFragment() {

    private lateinit var binding: FragmentShowEmployeeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowEmployeeBinding.inflate(layoutInflater)
        binding.employee = employee
        Glide.with(this).load(employee.image).placeholder(R.drawable.img_not_found)
            .error(R.drawable.img_not_found).into(binding.imgEmployee)
        binding.txtCreated.text = utcToLocal(employee.createdDate)
        return binding.root
    }
}