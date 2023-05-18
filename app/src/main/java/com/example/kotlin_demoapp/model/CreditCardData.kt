package com.example.kotlin_demoapp.model

import android.graphics.drawable.Drawable

data class CreditCardData(
    var name: String,
    var image: Drawable,
    var number: Int,
    var balance: Float
)
