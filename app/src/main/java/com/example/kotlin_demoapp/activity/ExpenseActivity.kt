package com.example.kotlin_demoapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.DataBindingUtil
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.databinding.ActivityExpenseBinding
import com.example.kotlin_demoapp.databinding.CardCategoryBinding
import com.example.kotlin_demoapp.databinding.CardCategoryDownBinding
import com.example.kotlin_demoapp.model.CategoryData

class ExpenseActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityExpenseBinding
    private lateinit var categoryData: MutableList<CategoryData>
    private lateinit var categoryDownData: MutableList<CategoryData>
    private lateinit var categoryBinding: List<CardCategoryBinding>
    private lateinit var categoryDownBinding: List<CardCategoryDownBinding>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_expense)
        setBindings()

        binding.btnBack.setOnClickListener(this)
        getData()
        setExpenses()
        setTitle()
    }

    private fun setBindings() {
        categoryBinding = listOf(
            binding.itemTop,
            binding.itemTopRight,
            binding.itemRight,
            binding.itemLeft,
            binding.itemTopLeft
        )

        categoryDownBinding = listOf(
            binding.itemBottomRight,
            binding.itemBottom,
            binding.itemBottomLeft
        )
    }

    private fun getData() {
        categoryData = mutableListOf(
            CategoryData("Internet",AppCompatResources.getDrawable(this,R.drawable.icon_world),9),
            CategoryData("Grocery",AppCompatResources.getDrawable(this,R.drawable.icon_world),24),
            CategoryData("Taxi",AppCompatResources.getDrawable(this,R.drawable.icon_world),14),
            CategoryData("Entertainment",AppCompatResources.getDrawable(this,R.drawable.icon_world),24),
            CategoryData("Cloths",AppCompatResources.getDrawable(this,R.drawable.icon_world),4)
        )

        categoryDownData = mutableListOf(
            CategoryData("Restaurant",AppCompatResources.getDrawable(this,R.drawable.icon_world),9),
            CategoryData("Sport",AppCompatResources.getDrawable(this,R.drawable.icon_world),13),
            CategoryData("Alcohol",AppCompatResources.getDrawable(this,R.drawable.icon_world),3)
        )
    }

    private fun setTitle() {
        binding.txtTitleMonthSpending.text = getString(R.string.month_spending_title, "April")
        binding.txtBalance.text = getString(R.string.dollarFloatNumber, 841.90)
        binding.txtMonthSpendingPercent.text = getString(R.string.intPercent, 13)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.btnBack.id -> finish()
        }
    }


    private fun setExpenses() {
        categoryBinding.forEachIndexed{ position, it ->
            it.category = categoryData[position]
        }
        categoryDownBinding.forEachIndexed{ position, it ->
            it.category = categoryDownData[position]
        }
    }
}