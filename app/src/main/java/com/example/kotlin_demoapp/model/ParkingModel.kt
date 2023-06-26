package com.example.kotlin_demoapp.model

data class ParkingModel(
    val citationID: String,
    val facility: String,
    val dueDate: String,
    var status: Boolean,
    val fineAmount: Int,
    var checked: Boolean
)