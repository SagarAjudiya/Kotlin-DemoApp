package com.example.kotlin_demoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.content.res.AppCompatResources
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
    private lateinit var userDataList: MutableList<UserData>
    private lateinit var creditCardList: MutableList<CreditCardData>
    private lateinit var monthSpendingData: MonthSpendingData
    private lateinit var goalDataList: MutableList<GoalData>

    private lateinit var userBindings: List<CardUsersBinding>
    private lateinit var creditCardBindings: List<CardCreditCardBinding>
    private lateinit var goalBindings: List<CardGoalBinding>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setBindings()

        binding.cardMonthSpending.root.setOnClickListener(this)
        binding.btnNotification.setOnClickListener(this)
        getData()
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

    private fun setMonthSpending() {
        binding.cardMonthSpending.monthSpending = monthSpendingData
    }

    private fun getData() {
        userDataList = mutableListOf(
            UserData("Jackie",  AppCompatResources.getDrawable(this,R.drawable.img_avatar_1)),
            UserData("Michel", AppCompatResources.getDrawable(this,R.drawable.img_avatar_2)),
            UserData("Andy", AppCompatResources.getDrawable(this,R.drawable.img_avatar_3)),
            UserData("Marta", AppCompatResources.getDrawable(this,R.drawable.img_avatar_4)),
            UserData("Bob", AppCompatResources.getDrawable(this,R.drawable.img_avatar_boy)),
            UserData("Lily", AppCompatResources.getDrawable(this,R.drawable.img_avatar_girl)),
            UserData("Donald", AppCompatResources.getDrawable(this,R.drawable.img_avatar_1))
        )

        creditCardList = mutableListOf(
            CreditCardData("Mastercard", AppCompatResources.getDrawable(this,R.drawable.icon_mastercard), 5300,1600.32F),
            CreditCardData("Visa", AppCompatResources.getDrawable(this,R.drawable.icon_visa), 2405,32.92F),
            CreditCardData("American Express", AppCompatResources.getDrawable(this,R.drawable.icons_american_express), 6584,365.98F)
        )

        goalDataList = mutableListOf(
            GoalData("Tesla Model S", 45940,120000),
            GoalData("Royal Enfield", 950,2500),
            GoalData("Aston Martin DBX", 150,365000)
        )

        monthSpendingData = MonthSpendingData("April", 841.90F,13)
    }

    private fun setCreditCards() {
        creditCardBindings.forEachIndexed { position, it ->
            it.creditCard = creditCardList[position]
        }
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

    private fun setGoals() {
        goalBindings.forEachIndexed { position, it ->
            it.goal = goalDataList[position]
        }
    }

    private fun setUsers() {
        userBindings.forEachIndexed { position, it ->
            it.user = userDataList[position]
        }
    }
}