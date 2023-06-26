package com.example.kotlin_demoapp.tagb.module.authentication.model.request

import com.google.gson.annotations.SerializedName

data class SignUpRequestModel(
    val firstName: String,
    val lastName: String,
    val email: String,
    @SerializedName("phone")
    val mobileNumber: String,
    val password: String,
    val businessName: String = "business",
    @SerializedName("address")
    val businessAddress: String = "businessAddress",
    val country: String = "India",
    val state: String = "state",
    val city: String = "city",
    @SerializedName("zipcode")
    val zipCode: String = "zipcode",
    val userType: String = "business",
    val newsLetterAlert: Boolean = false,
    val registrationPlatform: String = "email",
    val termsAgreement: Boolean = true,
    val platform: String = "IOS",
    val deviceId: String = "test_android",
    val deviceToken: String = "Bad device token",
    val phoneExtension: String = "+91"
)
