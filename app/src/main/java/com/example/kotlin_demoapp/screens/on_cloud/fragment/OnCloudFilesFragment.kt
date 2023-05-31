package com.example.kotlin_demoapp.screens.on_cloud.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.kotlin_demoapp.databinding.FragmentOnCloudFilesBinding
import com.example.kotlin_demoapp.screens.on_cloud.model.TransferDataModel

class OnCloudFilesFragment : Fragment() {

    private lateinit var binding: FragmentOnCloudFilesBinding
    private val args by navArgs<OnCloudFilesFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnCloudFilesBinding.inflate(layoutInflater)
        binding.btnGo.setOnClickListener {
            val bottomSheet = NameSurnameBottomSheetFragment { name, surname ->
                val dialogFragment = NameSurnameDialogFragment(TransferDataModel(name, surname)) {
                    if (it) {
                        binding.txtName.text = name
                        binding.txtSurname.text = surname
                        setData()
                    }
                }
                dialogFragment.show(parentFragmentManager, dialogFragment.tag)
            }
            bottomSheet.show(parentFragmentManager, bottomSheet.tag)
        }
        if (args.transferData != null) {
            setData()
            binding.transferData = args.transferData
        } else getData()
        return binding.root
    }

    private fun setData() {
        binding.txtName.visibility = View.VISIBLE
        binding.txtSurname.visibility = View.VISIBLE
        binding.txtEmpty.visibility = View.GONE
        binding.btnGo.visibility = View.GONE
    }

    private fun getData() {
        binding.txtName.visibility = View.GONE
        binding.txtSurname.visibility = View.GONE
        binding.txtEmpty.visibility = View.VISIBLE
        binding.btnGo.visibility = View.VISIBLE
    }
}