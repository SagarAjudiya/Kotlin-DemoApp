package com.example.kotlin_demoapp

import android.content.Context
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.content.res.AppCompatResources
import com.example.kotlin_demoapp.enums.CategoryDirection
import com.example.kotlin_demoapp.enums.YouTubeItemType
import com.example.kotlin_demoapp.model.CategoryData
import com.example.kotlin_demoapp.model.CommentModel
import com.example.kotlin_demoapp.model.CreditCardData
import com.example.kotlin_demoapp.model.GoalData
import com.example.kotlin_demoapp.model.MonthSpendingData
import com.example.kotlin_demoapp.model.NormalModel
import com.example.kotlin_demoapp.model.ProfileData
import com.example.kotlin_demoapp.model.SnapImagesModel
import com.example.kotlin_demoapp.model.UserData
import com.example.kotlin_demoapp.model.ValueChange
import com.example.kotlin_demoapp.model.YouTubeModel

object Helper {

    fun getCategoryData(context: Context) = mutableListOf(
        CategoryData(
            "Internet",
            AppCompatResources.getDrawable(context, R.drawable.icon_world) ?: ColorDrawable(),
            9,
            CategoryDirection.UP
        ),
        CategoryData(
            "Grocery",
            AppCompatResources.getDrawable(context, R.drawable.icon_world) ?: ColorDrawable(),
            24,
            CategoryDirection.UP
        ),
        CategoryData(
            "Taxi",
            AppCompatResources.getDrawable(context, R.drawable.icon_world) ?: ColorDrawable(),
            14,
            CategoryDirection.UP
        ),
        CategoryData(
            "Restaurant",
            AppCompatResources.getDrawable(context, R.drawable.icon_world) ?: ColorDrawable(),
            9,
            CategoryDirection.DOWN
        ),
        CategoryData(
            "Sport",
            AppCompatResources.getDrawable(context, R.drawable.icon_world) ?: ColorDrawable(),
            13,
            CategoryDirection.DOWN
        ),
        CategoryData(
            "Alcohol",
            AppCompatResources.getDrawable(context, R.drawable.icon_world) ?: ColorDrawable(),
            3,
            CategoryDirection.DOWN
        ),
        CategoryData(
            "Entertainment",
            AppCompatResources.getDrawable(context, R.drawable.icon_world) ?: ColorDrawable(), 24, CategoryDirection.UP
        ),
        CategoryData(
            "Cloths",
            AppCompatResources.getDrawable(context, R.drawable.icon_world) ?: ColorDrawable(),
            4,
            CategoryDirection.UP
        )
    )

    fun getUserData(context: Context) = mutableListOf(
        UserData("Jackie", AppCompatResources.getDrawable(context, R.drawable.img_avatar_1) ?: ColorDrawable()),
        UserData("Michel", AppCompatResources.getDrawable(context, R.drawable.img_avatar_2) ?: ColorDrawable()),
        UserData("Andy", AppCompatResources.getDrawable(context, R.drawable.img_avatar_3) ?: ColorDrawable()),
    )

    fun getGoalDataList() = mutableListOf(
        GoalData("Tesla Model S", 45940, 120000),
        GoalData("Royal Enfield", 950, 2500),
        GoalData("Aston Martin DBX", 150, 365000)
    )

    fun getCreditCardData(context: Context) = mutableListOf(
        CreditCardData(
            "Mastercard",
            AppCompatResources.getDrawable(context, R.drawable.icon_mastercard) ?: ColorDrawable(),
            5300,
            1600.32F
        ),
        CreditCardData(
            "Visa",
            AppCompatResources.getDrawable(context, R.drawable.icon_visa) ?: ColorDrawable(),
            2405,
            32.92F
        ),
        CreditCardData(
            "American Express",
            AppCompatResources.getDrawable(context, R.drawable.icons_american_express) ?: ColorDrawable(),
            6584,
            365.98F
        )
    )

    fun getProfileData(context: Context) = ProfileData(
        "Sophie",
        AppCompatResources.getDrawable(context, R.drawable.img_avatar_girl) ?: ColorDrawable(),
        0.9028F,
        ValueChange.DECREASE,
        41.0010F,
        ValueChange.INCREASE
    )

    fun getMonthSpendingData(): MonthSpendingData = MonthSpendingData("April", 841.90F, 13)

    fun getYouTubeData(context: Context) = mutableListOf<YouTubeModel>(
        YouTubeModel(
            YouTubeItemType.NORMAL,
            NormalModel(AppCompatResources.getDrawable(context, R.drawable.img_avatar_boy) ?: ColorDrawable())
        ),
        YouTubeModel(
            YouTubeItemType.COMMENT, CommentModel(
                AppCompatResources.getDrawable(context, R.drawable.img_avatar_1) ?: ColorDrawable(),
                "Comment Title - 1",
                "This is description of comment 1. There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form."
            )
        ),
        YouTubeModel(
            YouTubeItemType.SNAP_IMAGES,
            SnapImagesModel(
                arrayListOf(
                    AppCompatResources.getDrawable(context, R.drawable.img_avatar_boy) ?: ColorDrawable(),
                    AppCompatResources.getDrawable(context, R.drawable.img_avatar_2) ?: ColorDrawable(),
                    AppCompatResources.getDrawable(context, R.drawable.img_avatar_3) ?: ColorDrawable(),
                    AppCompatResources.getDrawable(context, R.drawable.img_avatar_1) ?: ColorDrawable(),
                    AppCompatResources.getDrawable(context, R.drawable.img_avatar_boy) ?: ColorDrawable()
                )
            )
        ),
        YouTubeModel(
            YouTubeItemType.COMMENT, CommentModel(
                AppCompatResources.getDrawable(context, R.drawable.img_avatar_1) ?: ColorDrawable(),
                "Comment Title - 2",
                "This is description of comment 1. There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form."
            )
        ),
        YouTubeModel(
            YouTubeItemType.NORMAL,
            NormalModel(AppCompatResources.getDrawable(context, R.drawable.img_avatar_1) ?: ColorDrawable())
        ),
        YouTubeModel(
            YouTubeItemType.NORMAL,
            NormalModel(AppCompatResources.getDrawable(context, R.drawable.img_avatar_girl) ?: ColorDrawable())
        ),
        YouTubeModel(
            YouTubeItemType.SNAP_IMAGES,
            SnapImagesModel(
                arrayListOf(
                    AppCompatResources.getDrawable(context, R.drawable.img_avatar_boy) ?: ColorDrawable(),
                    AppCompatResources.getDrawable(context, R.drawable.img_avatar_2) ?: ColorDrawable(),
                    AppCompatResources.getDrawable(context, R.drawable.img_avatar_3) ?: ColorDrawable(),
                )
            )
        ),
        YouTubeModel(
            YouTubeItemType.COMMENT, CommentModel(
                AppCompatResources.getDrawable(context, R.drawable.img_avatar_1) ?: ColorDrawable(),
                "Comment Title - 2",
                "This is description of comment 1. There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form."
            )
        ),
    )
}