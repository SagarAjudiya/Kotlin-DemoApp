package com.example.kotlin_demoapp.screens.on_cloud.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlin_demoapp.databinding.FragmentNameSurnameBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NameSurnameBottomSheetFragment(private val handler: (String, String) -> Unit) : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentNameSurnameBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNameSurnameBottomSheetBinding.inflate(layoutInflater)
        binding.btnSubmit.setOnClickListener {
            handler(binding.etName.text.toString().ifEmpty { "NAME" }, binding.etSurname.text.toString().ifEmpty { "SURNAME" })
            dismiss()
        }
        return binding.root
    }
}