package com.example.wanted_preonboarding_android.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AbsListView
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wanted_preonboarding_android.R
import com.example.wanted_preonboarding_android.adapter.NewsAdapter
import com.example.wanted_preonboarding_android.ui.MainActivity
import com.example.wanted_preonboarding_android.ui.NewsViewModel
import com.example.wanted_preonboarding_android.utils.Constants.Companion.QUERY_PAGE_SIZE
import com.example.wanted_preonboarding_android.utils.Resource
import com.google.android.material.appbar.MaterialToolbar

class TopHeadlineNewsFragment : Fragment(R.layout.fragment_topheadline_news) {

    private val viewModel : NewsViewModel by activityViewModels()
    lateinit var newsAdapter: NewsAdapter
    lateinit var rvTopHeadlineNews: RecyclerView
    lateinit var paginationProgressBar: ProgressBar
    lateinit var tbTitleBar: MaterialToolbar

    val TAG = "TopHeadlineNewsFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tbTitleBar = view.findViewById(R.id.tb_title_bar)
        tbTitleBar.title = getString(R.string.navigation_topheadline)
        rvTopHeadlineNews = view.findViewById(R.id.rv_news)
        setupRecyclerView()

        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }

            findNavController().navigate(
                R.id.action_topHeadlineNewsFragment_to_articleFragment,
                bundle
            )
        }

        paginationProgressBar = view.findViewById(R.id.paginationProgressBar)
        viewModel.topHeadlinesNews.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar(view)
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles.toList())
                        val totalPages = newsResponse.totalResults / QUERY_PAGE_SIZE + 2
                        isLastPage = viewModel.topHeadlinesNewsPage == totalPages
                        if (isLastPage) {
                            rvTopHeadlineNews?.setPadding(0, 0, 0, 0)
                        }
                    }
                }
                is Resource.Error -> {
                    hideProgressBar(view)
                    response.message?.let { message ->
                        Log.e(TAG, "An error accrued: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar(view)
                }
            }
        })
    }

    private fun hideProgressBar(view: View) {
        paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar(view: View) {
        paginationProgressBar.visibility = View.VISIBLE
    }

    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    var scrollListner = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)

            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNotLoadingAndNotLasgPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= QUERY_PAGE_SIZE
            val sholdPaginate =
                isNotLoadingAndNotLasgPage && isAtLastItem && isNotAtBeginning && isTotalMoreThanVisible && isScrolling
            if (sholdPaginate) {
                viewModel.getTopHeadlineNews("us")
                isScrolling = false
            }
        }
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        rvTopHeadlineNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(this@TopHeadlineNewsFragment.scrollListner)
        }
    }
}