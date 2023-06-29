package com.example.kotlin_demoapp.tagb.module.dashboard.view

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.databinding.FragmentUserBinding
import com.example.kotlin_demoapp.tagb.adapter.UserRecyclerAdapter
import com.example.kotlin_demoapp.tagb.base_classes.BaseFragment
import com.example.kotlin_demoapp.tagb.module.dashboard.viewModel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserFragment : BaseFragment<FragmentUserBinding, UserViewModel>() {

    private lateinit var adapter: UserRecyclerAdapter
    override fun setViewModel(): UserViewModel? = ViewModelProvider(this)[UserViewModel::class.java]

    override fun getResId(): Int = R.layout.fragment_user

    override fun setUpView() {
        adapter = UserRecyclerAdapter()
        binding.userRecycler.adapter = adapter
        addPullToRefresh()
        refreshData()
        addObserver()
    }

    private fun addObserver() {
        viewModel.responseData.observe(viewLifecycleOwner) { userList ->
            adapter.submitList(userList.toMutableList())
            binding.shimmerLayout.apply {
                stopShimmer()
                visibility = View.GONE
            }
            binding.userRecycler.visibility = View.VISIBLE
        }
    }

    private fun addPullToRefresh() {
        binding.pullToRefresh.setOnRefreshListener {
            refreshData()
            binding.pullToRefresh.isRefreshing = false
        }
    }

    private fun refreshData() {
        binding.userRecycler.visibility = View.GONE
        viewModel.getUser()
        binding.shimmerLayout.apply {
            startShimmer()
            visibility = View.VISIBLE
        }
    }
}