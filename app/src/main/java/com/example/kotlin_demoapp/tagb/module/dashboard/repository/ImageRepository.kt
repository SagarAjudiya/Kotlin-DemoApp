package com.example.kotlin_demoapp.tagb.module.dashboard.repository

import com.example.kotlin_demoapp.tagb.api_services.ImageService
import javax.inject.Inject

class ImageRepository @Inject constructor(
    private val service: ImageService
) {
}