package com.example.kotlin_demoapp.activity.components

import android.os.Bundle
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kotlin_demoapp.adapter.ImagePickerRecyclerAdapter
import com.example.kotlin_demoapp.databinding.ActivityImagePickerBinding
import com.example.kotlin_demoapp.model.ImageHolder

class ImagePicker : AppCompatActivity() {

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
        binding.imageRecycler.layoutManager = GridLayoutManager(this, 2)
        binding.imageRecycler.adapter = adapter

        binding.btnImagePicker.setOnClickListener {
            imagePickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
        }
    }
}