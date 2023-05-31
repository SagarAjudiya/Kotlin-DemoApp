package com.example.kotlin_demoapp.screens.on_cloud.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TransferDataModel(val name: String = "NAME", val surname: String = "SURNAME"): Parcelable
