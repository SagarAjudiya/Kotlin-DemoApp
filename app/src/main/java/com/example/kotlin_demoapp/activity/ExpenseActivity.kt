package com.example.kotlin_demoapp.activity

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.kotlin_demoapp.Helper
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.databinding.ActivityExpenseBinding
import com.example.kotlin_demoapp.databinding.CardCategoryBinding
import com.example.kotlin_demoapp.databinding.CardCategoryDownBinding
import com.example.kotlin_demoapp.enums.CategoryDirection

class ExpenseActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityExpenseBinding
    private lateinit var categoryBinding: MutableList<CardCategoryBinding>
    private lateinit var categoryDownBinding: MutableList<CardCategoryDownBinding>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_expense)
        setBindings()

        binding.btnBack.setOnClickListener(this)
        setExpenses()
        setTitle()
    }

    private fun setBindings() {
        categoryBinding = mutableListOf(
            binding.itemTop,
            binding.itemTopRight,
            binding.itemRight,
            binding.itemLeft,
            binding.itemTopLeft
        )

        categoryDownBinding = mutableListOf(
            binding.itemBottomRight,
            binding.itemBottom,
            binding.itemBottomLeft
        )
    }

    private fun setTitle() {
        binding.monthSpending = Helper.getMonthSpendingData()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.btnBack.id -> finish()
        }
    }


    private fun setExpenses() {
        val mutableList = Helper.getCategoryData(this)
        mutableList.forEach {
            if (it.type == CategoryDirection.UP) {
                categoryBinding.first().category = it
                categoryBinding.removeFirstOrNull()
            } else {
                categoryDownBinding.first().category = it
                categoryDownBinding.removeFirstOrNull()
            }
        }
    }
}