package com.example.kotlin_demoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.databinding.DataBindingUtil
import com.example.kotlin_demoapp.activity.ExpenseActivity
import com.example.kotlin_demoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.cardMonthSpending.setOnClickListener(this)
        binding.btnNotification.setOnClickListener(this)
        setCreditCards()
        setUsers()
        setGoals()
    }

    private fun setCreditCards() {
        binding.creditCard2.apply {
            imgCardType.setImageResource(R.drawable.icon_visa)
            txtBalance.text = getString(R.string.dollarFloatNumber, 32.62)
            txtCardType.text = getString(R.string.string, "Visa")
            txtCardNumber.text = getString(R.string.intNumber, 2400)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.cardMonthSpending.id -> startActivity(Intent(this, ExpenseActivity::class.java))
            binding.btnNotification.id -> {
                when(binding.btnNotificationDot.visibility){
                    View.VISIBLE -> binding.btnNotificationDot.visibility = View.GONE
                    View.GONE -> binding.btnNotificationDot.visibility = View.VISIBLE
                    View.INVISIBLE -> binding.btnNotificationDot.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setGoals() {
        binding.goal1.apply {
            txtProgress.text = getString(R.string.floatPercent, 56.2)
            goalProgress.progress = 56
        }

        binding.goal2.apply {
            txtGoalName.text = getString(R.string.string, "Tesla Model X")
            txtGoalTotalMoney.text = getString(R.string.goal_total_money, 20000)
        }

        binding.goal3.apply {
            txtGoalName.text = getString(R.string.string, "Tesla Model Z")
            txtGoalTotalMoney.text = getString(R.string.goal_total_money, 295000)
        }
    }

    private fun setUsers() {
        binding.user2.apply {
            imgUser.setImageResource(R.drawable.img_avatar_1)
            txtUserName.text = getString(R.string.string, "Michel")
        }

        binding.user3.apply {
            imgUser.setImageResource(R.drawable.img_avatar_2)
            txtUserName.text = getString(R.string.string, "Andy")
        }

        binding.user4.apply {
            imgUser.setImageResource(R.drawable.img_avatar_3)
            txtUserName.text = getString(R.string.string, "Marta")
        }

        binding.user5.apply {
            imgUser.setImageResource(R.drawable.img_avatar_4)
            txtUserName.text = getString(R.string.string, "Bob")
        }

        binding.user6.apply {
            imgUser.setImageResource(R.drawable.img_avatar_1)
            txtUserName.text = getString(R.string.string, "Michel")
        }

        binding.user7.apply {
            imgUser.setImageResource(R.drawable.img_avatar_2)
            txtUserName.text = getString(R.string.string, "Abby")
        }
    }
}