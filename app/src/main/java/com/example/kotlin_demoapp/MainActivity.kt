package com.example.kotlin_demoapp

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.WindowCompat
import androidx.databinding.DataBindingUtil
import com.example.kotlin_demoapp.activity.ExpenseActivity
import com.example.kotlin_demoapp.databinding.ActivityMainBinding
import com.example.kotlin_demoapp.databinding.CardCreditCardBinding
import com.example.kotlin_demoapp.databinding.CardGoalBinding
import com.example.kotlin_demoapp.databinding.CardUsersBinding
import com.example.kotlin_demoapp.model.CreditCardData
import com.example.kotlin_demoapp.model.GoalData
import com.example.kotlin_demoapp.model.MonthSpendingData
import com.example.kotlin_demoapp.model.UserData

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var userBindings: List<CardUsersBinding>
    private lateinit var creditCardBindings: List<CardCreditCardBinding>
    private lateinit var goalBindings: List<CardGoalBinding>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        // Transparent Navigation and StatusBar
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setBindings()

        binding.cardMonthSpending.root.setOnClickListener(this)
        binding.btnNotification.setOnClickListener(this)
        setProfile()
        setCreditCards()
        setUsers()
        setGoals()
        setMonthSpending()
    }

    private fun setBindings() {
        userBindings = listOf(
            binding.user1,
            binding.user2,
            binding.user3,
            binding.user4,
            binding.user5,
            binding.user6,
            binding.user7
        )
        creditCardBindings =
            listOf(binding.creditCard1, binding.creditCard2, binding.creditCard3)
        goalBindings = listOf(binding.goal1, binding.goal2, binding.goal3)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.cardMonthSpending.root.id -> startActivity(Intent(this, ExpenseActivity::class.java))
            binding.btnNotification.id -> {
                when (binding.btnNotificationDot.visibility) {
                    View.VISIBLE -> binding.btnNotificationDot.visibility = View.GONE
                    View.GONE -> binding.btnNotificationDot.visibility = View.VISIBLE
                    View.INVISIBLE -> binding.btnNotificationDot.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setProfile() {
        binding.profile = Helper.getProfileData(this)
    }

    private fun setMonthSpending() {
        binding.cardMonthSpending.monthSpending = Helper.getMonthSpendingData()
    }

    private fun setCreditCards() {
        creditCardBindings.forEachIndexed { position, it ->
            it.creditCard = Helper.getCreditCardData(this)[position]
        }
    }

    private fun setGoals() {
        goalBindings.forEachIndexed { position, it ->
            it.goal = Helper.getGoalDataList()[position]
        }
    }

    private fun setUsers() {
        userBindings.forEachIndexed { position, it ->
            it.user = Helper.getUserData(this)[position]
        }
    }
}