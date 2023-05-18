package com.example.kotlin_demoapp.model

data class GoalData(var name: String, var currentBalance: Int, var totalGoalMoney: Int) {
    val percent: Float
        get() = (currentBalance.toFloat() / totalGoalMoney.toFloat()) * 100
}
