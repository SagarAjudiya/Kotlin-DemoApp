package com.example.kotlin_demoapp.tagb.module.dashboard.view

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_demoapp.R
import com.example.kotlin_demoapp.databinding.FragmentMovieBinding
import com.example.kotlin_demoapp.tagb.adapter.MovieRecyclerAdapter
import com.example.kotlin_demoapp.tagb.base_classes.BaseFragment
import com.example.kotlin_demoapp.tagb.helper.UserDefault
import com.example.kotlin_demoapp.tagb.module.dashboard.model.response.MovieInfo
import com.example.kotlin_demoapp.tagb.module.dashboard.viewModel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : BaseFragment<FragmentMovieBinding, MovieViewModel>() {

    private lateinit var adapter: MovieRecyclerAdapter
    private var page: Int = 1
    private var isLoading = false
    private var movieList = mutableListOf<MovieInfo>()

    override fun setViewModel(): MovieViewModel =
        ViewModelProvider(this)[MovieViewModel::class.java]

    override fun getResId(): Int = R.layout.fragment_movie

    override fun setUpView() {
        adapter = MovieRecyclerAdapter()
        binding.movieRecycler.adapter = adapter
        addOnScrollListener()
        addPullToRefresh()
        refreshData()
        addObserver()
    }

    private fun addOnScrollListener() {
        binding.movieRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == movieList.count() - 1) {
                        binding.progressBarPagination.visibility = View.VISIBLE
                        viewModel.getMovieList(UserDefault.MOVIE_ACCESS_TOKEN, ++page)
                        isLoading = true
                    }
                }
            }
        })
    }

    private fun addObserver() {
        viewModel.fetchListSuccess.observe(viewLifecycleOwner) { newMovieList ->
            if (newMovieList.isNotEmpty()) {
                if (page == 1) {
                    movieList.clear()
                }
                movieList.addAll(newMovieList)
                adapter.submitList(movieList)
                binding.progressBarPagination.visibility = View.GONE
            }
            binding.movieRecycler.visibility = View.VISIBLE
            binding.shimmerLayout.apply {
                stopShimmer()
                visibility = View.GONE
            }
            isLoading = false
        }

        viewModel.fetchListFailure.observe(this) {
            showError(it)
        }
    }

    private fun addPullToRefresh() {
        binding.pullToRefresh.setOnRefreshListener {
            refreshData()
            binding.pullToRefresh.isRefreshing = false
        }
    }

    private fun refreshData() {
        binding.movieRecycler.visibility = View.GONE
        binding.progressBarPagination.visibility = View.GONE
        page = 1
        viewModel.getMovieList(UserDefault.MOVIE_ACCESS_TOKEN, page)
        binding.shimmerLayout.apply {
            startShimmer()
            visibility = View.VISIBLE
        }
    }
}