package com.example.kotlin_demoapp.model

import android.graphics.drawable.Drawable

data class CategoryData(var name: String, var image: Drawable?, var percent: Int, var type: CategoryDirection)
