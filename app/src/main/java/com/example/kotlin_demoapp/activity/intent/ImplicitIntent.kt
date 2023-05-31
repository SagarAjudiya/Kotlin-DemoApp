package com.example.kotlin_demoapp.activity.intent

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.kotlin_demoapp.databinding.ActivityImplicitIntentBinding
import java.io.File

class ImplicitIntent : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityImplicitIntentBinding
    private lateinit var imageUri: Uri
    private lateinit var videoUri: Uri
    private var imageCapture =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode != RESULT_OK) return@registerForActivityResult
            binding.imgCapture.setImageURI(null)
            binding.imgCapture.setImageURI(imageUri)
        }
    private var videoCapture =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode != RESULT_OK) return@registerForActivityResult
            binding.videoCapture.setVideoURI(null)
            binding.videoCapture.setVideoURI(videoUri)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityImplicitIntentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.action == Intent.ACTION_SEND) {
            Toast.makeText(this, intent.extras?.getString(Intent.EXTRA_TEXT),Toast.LENGTH_SHORT).show()
        }

        imageUri = createImageUri()
        videoUri = createVideoUri()

        binding.btnWebsite.setOnClickListener(this)
        binding.btnCall.setOnClickListener(this)
        binding.btnCameraImage.setOnClickListener(this)
        binding.btnCameraVideo.setOnClickListener(this)
        binding.videoCapture.setOnClickListener(this)
        binding.btnLocation.setOnClickListener(this)
        binding.btnText.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.btnWebsite.id -> {
                val url = "https://www." + binding.editWebsite.text.toString()
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                val chooser = Intent.createChooser(intent, "Choose Browser")
                startActivity(chooser)
            }

            binding.btnCall.id -> {
                val url = "tel:" + binding.editCall.text.toString()
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse(url))
                val chooser = Intent.createChooser(intent, "Choose App")
                startActivity(chooser)
            }

            binding.btnCameraImage.id -> {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
                imageCapture.launch(intent)
            }

            binding.btnCameraVideo.id -> {
                val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
                intent.putExtra(MediaStore.EXTRA_OUTPUT, videoUri)
                videoCapture.launch(intent)
            }

            binding.videoCapture.id -> {
                binding.videoCapture.start()
            }

            binding.btnLocation.id -> {
                val location = "geo:0,0?q=" + binding.etLocationName.text.toString().ifEmpty { "Simform, Ahmedabad" }
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(location))
                startActivity(intent)
            }

            binding.btnText.id -> {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_SUBJECT, "SOME SUBJECT")
                    putExtra(Intent.EXTRA_TEXT, binding.etText.text.toString().ifEmpty { "DEFAULT TEXT" })
                }
                startActivity(Intent.createChooser(intent, "Choose App"))
            }
        }
    }

    private fun createImageUri(): Uri {
        val image = File(applicationContext.filesDir, "camera_photo.png")
        return FileProvider.getUriForFile(
            applicationContext,
            applicationContext.packageName + ".fileProvider",
            image
        )
    }

    private fun createVideoUri(): Uri {
        val video = File(applicationContext.filesDir, "camera_video.mp4")
        return FileProvider.getUriForFile(
            applicationContext,
            applicationContext.packageName + ".fileProvider",
            video
        )
    }
}