package com.example.kotlin_demoapp.screens.on_cloud.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.databinding.FragmentNameSurnameDialogBinding
import com.example.kotlin_demoapp.screens.on_cloud.model.TransferDataModel

class NameSurnameDialogFragment(private val data: TransferDataModel, private val confirmation: (Boolean) -> Unit) : DialogFragment() {

    private lateinit var binding: FragmentNameSurnameDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNameSurnameDialogBinding.inflate(layoutInflater)
        binding.txtName.text = data.name
        binding.txtSurname.text = data.surname
        binding.btnCancel.setOnClickListener {
            confirmation(false)
            dismiss()
        }
        binding.btnSubmit.setOnClickListener {
            confirmation(true)
            dismiss()
        }
        return binding.root
    }
}