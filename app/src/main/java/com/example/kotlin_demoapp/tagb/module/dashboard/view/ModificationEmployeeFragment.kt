package com.example.kotlin_demoapp.tagb.module.dashboard.view

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.databinding.FragmentModificationEmployeeBinding
import com.example.kotlin_demoapp.tagb.helper.getFileName
import com.example.kotlin_demoapp.tagb.helper.getStoragePermission
import java.io.File

class ModificationEmployeeFragment(
    val title: String,
    val name: String = "",
    val image: String = "",
    private val btnRightCompletion: (name: String, image: Uri?) -> Unit
) : DialogFragment() {

    private lateinit var binding: FragmentModificationEmployeeBinding
    private var uri: Uri? = null
    private val imagePickerLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { result ->
        if (result != null) {
            uri = result
            Glide.with(this)
                .load(activity?.contentResolver?.getFileName(result)?.let {
                    File(
                        activity?.cacheDir,
                        it
                    )
                })
                .placeholder(R.drawable.img_not_found)
                .error(R.drawable.img_not_found)
                .into(binding.imgEmployee)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentModificationEmployeeBinding.inflate(layoutInflater)
        binding.callback = this
        Glide.with(this)
            .load(image)
            .placeholder(R.drawable.img_not_found)
            .error(R.drawable.img_not_found)
            .into(binding.imgEmployee)
        return binding.root
    }

    fun doneClicked() {
        btnRightCompletion(binding.etName.text.toString(), uri)
        dismiss()
    }

    fun cancelClicked() {
        dismiss()
    }

    fun selectImage() {
        activity?.let {
            if (getStoragePermission(it, getString(R.string.storage_permission_denied))) {
                imagePickerLauncher.launch("image/*")
            }
        }
    }
}
