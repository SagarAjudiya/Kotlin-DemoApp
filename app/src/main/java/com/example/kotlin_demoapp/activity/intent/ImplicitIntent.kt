package com.example.kotlin_demoapp.activity.intent

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.media.ThumbnailUtils
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.databinding.ActivityImplicitIntentBinding
import com.example.kotlin_demoapp.tagb.helper.getCameraPermission
import com.example.kotlin_demoapp.tagb.helper.getCurrentTime
import com.google.android.material.snackbar.Snackbar
import java.io.File
import java.text.SimpleDateFormat
import java.util.regex.Pattern

class ImplicitIntent : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityImplicitIntentBinding
    private lateinit var currentImagePath: String
    private lateinit var videoUri: Uri
    private var imageCapture =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { isPictureTaken ->
            if (isPictureTaken) {
                binding.imgCapture.setImageBitmap(BitmapFactory.decodeFile(currentImagePath))
            } else {
                Toast.makeText(this, "Picture is not taken !!", Toast.LENGTH_SHORT).show()
            }
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
            Toast.makeText(this, intent.extras?.getString(Intent.EXTRA_TEXT), Toast.LENGTH_SHORT)
                .show()
        }

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
                val chooser = Intent.createChooser(intent, getString(R.string.choose_browser))
                startActivity(chooser)
            }

            binding.btnCall.id -> {
                val url = "tel:" + binding.editCall.text.toString()
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse(url))
                val chooser = Intent.createChooser(intent, getString(R.string.choose_app))
                startActivity(chooser)
            }

            binding.btnCameraImage.id -> {
                if (getCameraPermission(this)) {
                    imageCapture.launch(createImageUri())
                } else {
                    Snackbar.make(binding.root, getString(R.string.camera_permission_denied), Snackbar.LENGTH_SHORT).show()
                }
            }

            binding.btnCameraVideo.id -> {
                if (getCameraPermission(this)) {
                    val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, videoUri)
                    videoCapture.launch(intent)
                } else {
                    Snackbar.make(binding.root, getString(R.string.camera_permission_denied), Snackbar.LENGTH_SHORT).show()
                }
            }

            binding.videoCapture.id -> {
                binding.videoCapture.start()
            }

            binding.btnLocation.id -> {
                val location = "geo:0,0?q=" + binding.etLocationName.text.toString().ifEmpty {
                    getString(R.string.simform)
                }
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(location))
                startActivity(intent)
            }

            binding.btnText.id -> {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_SUBJECT, getString(R.string.some_subject))
                    putExtra(Intent.EXTRA_TEXT,
                        binding.etText.text.toString().ifEmpty { R.string.default_text })
                }
                startActivity(Intent.createChooser(intent, getString(R.string.choose_app)))
            }
        }
    }

    private fun createImageUri(): Uri {
        val folder = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val file = File.createTempFile("JPG_${getCurrentTime()}", ".jpg", folder)
        currentImagePath = file.absolutePath
        return FileProvider.getUriForFile(
            this,
            "${applicationContext.packageName}.fileProvider",
            file
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