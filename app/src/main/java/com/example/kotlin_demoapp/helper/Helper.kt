package com.example.kotlin_demoapp.helper

import com.example.kotlin_demoapp.model.ParkingModel

object Helper {

    fun getSiteList() =
        arrayOf("google", "yahoo", "gmail", "youtube", "apple", "facebook", "twitter", "instagram")

    fun getParkingCitationData() = listOf<ParkingModel>(
        ParkingModel(
            "RUCHIT",
            "2434 - 179 W Washington St., Valet",
            "Oct 18, 2020",
            true,
            40,
            false
        ),
        ParkingModel(
            "PARTH",
            "2434 - 179 W Washington St., Valet",
            "Oct 18, 2020",
            false,
            40,
            false
        ),
        ParkingModel(
            "TOSIF",
            "2434 - 179 W Washington St., Valet",
            "Oct 25, 2021",
            true,
            40,
            true
        ),
        ParkingModel(
            "MEETRAJ",
            "2434 - 179 W Washington St., Valet",
            "Oct 18, 2020",
            false,
            40,
            true
        ),
        ParkingModel(
            "RAHUL",
            "2434 - 179 W Washington St., Valet",
            "Oct 18, 2020",
            false,
            40,
            true
        ),
        ParkingModel(
            "ANIKET",
            "2434 - 179 W Washington St., Valet",
            "Oct 18, 2020",
            true,
            40,
            false
        ),
        ParkingModel(
            "RUCHIT",
            "2434 - 179 W Washington St., Valet",
            "Oct 18, 2020",
            false,
            40,
            false
        ),
        ParkingModel(
            "PARTH",
            "2434 - 179 W Washington St., Valet",
            "Oct 18, 2020",
            false,
            40,
            true
        ),
        ParkingModel(
            "TOSIF",
            "2434 - 179 W Washington St., Valet",
            "Oct 18, 2020",
            false,
            40,
            false
        )
    )
}