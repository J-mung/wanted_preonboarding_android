package com.example.wanted_preonboarding_android.ui.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.wanted_preonboarding_android.R
import com.example.wanted_preonboarding_android.ui.MainActivity
import com.example.wanted_preonboarding_android.ui.NewsViewModel

class ArticleFragment : Fragment(R.layout.fragment_article) {
    lateinit var viewModel: NewsViewModel
    lateinit var webView: WebView
    val args: ArticleFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        val article = args.article
        webView = view.findViewById<WebView>(R.id.webView)
        webView.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url)
        }

    }
}