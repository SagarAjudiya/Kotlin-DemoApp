package com.example.kotlin_demoapp

import android.content.Context
import androidx.appcompat.content.res.AppCompatResources
import com.example.kotlin_demoapp.model.CategoryData
import com.example.kotlin_demoapp.model.CategoryDirection
import com.example.kotlin_demoapp.model.CreditCardData
import com.example.kotlin_demoapp.model.GoalData
import com.example.kotlin_demoapp.model.MonthSpendingData
import com.example.kotlin_demoapp.model.ProfileData
import com.example.kotlin_demoapp.model.UserData
import com.example.kotlin_demoapp.model.ValueChange

object Helper {

    fun getCategoryData(context: Context) = mutableListOf(
        CategoryData("Internet", AppCompatResources.getDrawable(context,R.drawable.icon_world),9, CategoryDirection.UP),
        CategoryData("Grocery", AppCompatResources.getDrawable(context,R.drawable.icon_world),24, CategoryDirection.UP),
        CategoryData("Taxi", AppCompatResources.getDrawable(context,R.drawable.icon_world),14, CategoryDirection.UP),
        CategoryData("Restaurant", AppCompatResources.getDrawable(context,R.drawable.icon_world),9, CategoryDirection.DOWN),
        CategoryData("Sport", AppCompatResources.getDrawable(context,R.drawable.icon_world),13, CategoryDirection.DOWN),
        CategoryData("Alcohol", AppCompatResources.getDrawable(context,R.drawable.icon_world),3, CategoryDirection.DOWN),
        CategoryData("Entertainment",
            AppCompatResources.getDrawable(context,R.drawable.icon_world),24, CategoryDirection.UP),
        CategoryData("Cloths", AppCompatResources.getDrawable(context,R.drawable.icon_world),4, CategoryDirection.UP)
    )

    fun getUserData(context: Context) =  mutableListOf(
        UserData("Jackie",  AppCompatResources.getDrawable(context,R.drawable.img_avatar_1)),
        UserData("Michel", AppCompatResources.getDrawable(context,R.drawable.img_avatar_2)),
        UserData("Andy", AppCompatResources.getDrawable(context,R.drawable.img_avatar_3)),
        UserData("Marta", AppCompatResources.getDrawable(context,R.drawable.img_avatar_4)),
        UserData("Bob", AppCompatResources.getDrawable(context,R.drawable.img_avatar_boy)),
        UserData("Lily", AppCompatResources.getDrawable(context,R.drawable.img_avatar_girl)),
        UserData("Donald", AppCompatResources.getDrawable(context,R.drawable.img_avatar_1))
    )

    fun getGoalDataList() = mutableListOf(
        GoalData("Tesla Model S", 45940,120000),
        GoalData("Royal Enfield", 950,2500),
        GoalData("Aston Martin DBX", 150,365000)
    )

    fun getCreditCardData(context: Context) = mutableListOf(
        CreditCardData("Mastercard", AppCompatResources.getDrawable(context,R.drawable.icon_mastercard), 5300,1600.32F),
        CreditCardData("Visa", AppCompatResources.getDrawable(context,R.drawable.icon_visa), 2405,32.92F),
        CreditCardData("American Express", AppCompatResources.getDrawable(context,R.drawable.icons_american_express), 6584,365.98F)
    )

    fun getProfileData(context: Context) = ProfileData("Sophie", AppCompatResources.getDrawable(context,R.drawable.img_avatar_girl), 0.9028F, ValueChange.DECREASE ,41.0010F, ValueChange.INCREASE)

    fun getMonthSpendingData() : MonthSpendingData = MonthSpendingData("April", 841.90F,13)
}