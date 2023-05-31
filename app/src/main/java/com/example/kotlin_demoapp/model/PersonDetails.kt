package com.example.kotlin_demoapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PersonDetails(
    val name: String = "ENTER NAME",
    val surname: String = "ENTER SURNAME",
    val email: String = "ENTER EMAIL",
    val phone: String = "ENTER PHONE"
) : Parcelable
