package com.example.kotlin_demoapp

import android.content.Intent
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.WindowCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_demoapp.activity.ExpenseActivity
import com.example.kotlin_demoapp.activity.YouTubeActivity
import com.example.kotlin_demoapp.adapters.CreditCardRecyclerAdapter
import com.example.kotlin_demoapp.adapters.UserRecyclerAdapter
import com.example.kotlin_demoapp.databinding.ActivityMainBinding
import com.example.kotlin_demoapp.databinding.CardGoalBinding
import com.example.kotlin_demoapp.model.UserData

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var creditCardAdapter: CreditCardRecyclerAdapter
    private lateinit var userAdapter: UserRecyclerAdapter
    private lateinit var goalBindings: List<CardGoalBinding>
    private val userList: MutableList<UserData> by lazy {
        Helper.getUserData(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        // Transparent Navigation and StatusBar
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setBindings()
        setProfile()
        setGoals()
        setMonthSpending()
        binding.cardMonthSpending.root.setOnClickListener(this)
        binding.btnNotification.setOnClickListener(this)
    }

    private fun setBindings() {
        creditCardAdapter = CreditCardRecyclerAdapter()
        creditCardAdapter.submitList(Helper.getCreditCardData(this))
        binding.creditCardRecycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.creditCardRecycler.adapter = creditCardAdapter
        binding.creditCardRecycler.addItemDecoration(getDecorator())

        userAdapter = UserRecyclerAdapter(this::addUser)
        userAdapter.submitList(userList)
        binding.userRecycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.userRecycler.adapter = userAdapter
        binding.userRecycler.addItemDecoration(getDecorator())

        goalBindings = listOf(binding.goal1, binding.goal2, binding.goal3)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.cardMonthSpending.root.id -> startActivity(
                Intent(
                    this,
                    ExpenseActivity::class.java
                )
            )

            binding.btnNotification.id -> {
                startActivity(Intent(this, YouTubeActivity::class.java))
            }
        }
    }

    private fun setProfile() {
        binding.profile = Helper.getProfileData(this)
    }

    private fun setMonthSpending() {
        binding.cardMonthSpending.monthSpending = Helper.getMonthSpendingData()
    }

    private fun setGoals() {
        goalBindings.forEachIndexed { position, it ->
            it.goal = Helper.getGoalDataList()[position]
        }
    }

    private fun getDecorator() = object : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.left = resources.getDimensionPixelSize(R.dimen._20dp)
            } else if (parent.getChildAdapterPosition(view) == parent.adapter?.itemCount?.minus(1)) {
                outRect.left = resources.getDimensionPixelSize(R.dimen._10dp)
                outRect.right = resources.getDimensionPixelSize(R.dimen._20dp)
            } else {
                outRect.left = resources.getDimensionPixelSize(R.dimen._10dp)
            }
        }
    }

    private fun addUser() {
        userList.add(
            UserData(
                "New User",
                AppCompatResources.getDrawable(this, R.drawable.img_avatar_1) ?: ColorDrawable()
            )
        )
        userAdapter.submitList(userList)
    }
}