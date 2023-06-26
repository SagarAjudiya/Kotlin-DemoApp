package com.example.kotlin_demoapp.activity.components

import android.os.Bundle
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.adapter.ImagePickerRecyclerAdapter
import com.example.kotlin_demoapp.databinding.ActivityImagePickerBinding
import com.example.kotlin_demoapp.model.ImageHolder
import com.example.kotlin_demoapp.tagb.helper.getCameraPermission
import com.google.android.material.snackbar.Snackbar

class ImagePickerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImagePickerBinding
    private lateinit var adapter: ImagePickerRecyclerAdapter
    private val imagePickerLauncher = registerForActivityResult(
        ActivityResultContracts.PickMultipleVisualMedia(5)
    ) { result ->
        val pickedImages = ArrayList<ImageHolder>()
        result.forEach {
            pickedImages.add(ImageHolder(it))
        }
        val list = adapter.currentList.toMutableList()
        list.addAll(pickedImages)
        adapter.submitList(list)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityImagePickerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ImagePickerRecyclerAdapter()
        binding.imageRecycler.adapter = adapter

        binding.btnImagePicker.setOnClickListener {
            if (getCameraPermission(this)) {
                imagePickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
            } else {
                Snackbar.make(binding.root, getString(R.string.camera_permission_denied), Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}