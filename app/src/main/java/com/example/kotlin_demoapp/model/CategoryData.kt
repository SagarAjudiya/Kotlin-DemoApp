package com.example.kotlin_demoapp.model

import android.graphics.drawable.Drawable
import com.example.kotlin_demoapp.enums.CategoryDirection

data class CategoryData(
    var name: String,
    var image: Drawable,
    var percent: Int,
    var type: CategoryDirection
)
