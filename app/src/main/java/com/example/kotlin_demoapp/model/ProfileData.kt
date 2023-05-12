package com.example.kotlin_demoapp.model

import android.graphics.drawable.Drawable

data class ProfileData(var name: String, var image: Drawable?,  var usdEur: Float, var changeUsdEur: ValueChange, var btcUsd: Float, var changeBtcUsd: ValueChange)
