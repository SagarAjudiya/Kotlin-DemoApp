package com.example.kotlin_demoapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.databinding.DataBindingUtil
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.databinding.ActivityExpenseBinding

class ExpenseActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityExpenseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_expense)

        binding.btnBack.setOnClickListener(this)

        setExpenses()
        setTitle()
    }

    private fun setTitle() {
        binding.txtTitleMonthSpending.text = getString(R.string.month_spending_title, "April")
        binding.txtBalance.text = getString(R.string.dollarFloatNumber,841.90)
        binding.txtMonthSpendingPercent.text = getString(R.string.intPercent, 13)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            binding.btnBack.id -> finish()
        }
    }


    private fun setExpenses() {
        binding.itemTop.apply {
            txtMonthSpendingPercent.text = getString(R.string.intPercent, 9)
            txtCategoryName.text = getString(R.string.string, "Internet")
        }
        binding.itemTopRight.apply {
            txtMonthSpendingPercent.text = getString(R.string.intPercent, 24)
            txtCategoryName.text = getString(R.string.string, "Grocery")
        }
        binding.itemRight.apply {
            txtMonthSpendingPercent.text = getString(R.string.intPercent, 14)
            txtCategoryName.text = getString(R.string.string, "Taxi")
        }
        binding.itemBottomRight.apply {
            txtMonthSpendingPercent.text = getString(R.string.intPercent, 9)
            txtCategoryName.text = getString(R.string.string, "Restaurants")
        }
        binding.itemBottom.apply {
            txtMonthSpendingPercent.text = getString(R.string.intPercent, 13)
            txtCategoryName.text = getString(R.string.string, "Sport")
        }
        binding.itemBottomLeft.apply {
            txtMonthSpendingPercent.text = getString(R.string.intPercent, 3)
            txtCategoryName.text = getString(R.string.string, "Alcohol")
        }
        binding.itemTopLeft.apply {
            txtMonthSpendingPercent.text = getString(R.string.intPercent, 24)
            txtCategoryName.text = getString(R.string.string, "Entertainment")
        }
        binding.itemTopLeft.apply {
            txtMonthSpendingPercent.text = getString(R.string.intPercent, 4)
            txtCategoryName.text = getString(R.string.string, "Cloths")
        }
    }
}