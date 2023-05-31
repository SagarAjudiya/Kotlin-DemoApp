package com.example.kotlin_demoapp.activity.components

import android.annotation.SuppressLint
import android.app.SearchManager
import android.database.MatrixCursor
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.BaseColumns
import android.view.View
import android.view.View.OnClickListener
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.CursorAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.kotlin_demoapp.adapter.SingleTextCursorAdapter
import com.example.kotlin_demoapp.databinding.ActivityWebViewBinding
import com.example.kotlin_demoapp.helper.Helper

class WebViewActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityWebViewBinding
    private lateinit var cursorAdapter: SingleTextCursorAdapter

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.isEnabled = false
        binding.btnForward.isEnabled = false
        binding.btnBack.setOnClickListener(this)
        binding.btnForward.setOnClickListener(this)
        binding.btnRefresh.setOnClickListener(this)
        val matrixCursor =
            MatrixCursor(arrayOf(BaseColumns._ID, SearchManager.SUGGEST_COLUMN_TEXT_1))
        Helper.getSiteList().forEachIndexed { index, siteName ->
            matrixCursor.addRow(arrayOf(index, siteName))
        }
        cursorAdapter = SingleTextCursorAdapter(this, matrixCursor, 0) {
            binding.searchView.setQuery(it, false)
        }
        binding.searchView.suggestionsAdapter = cursorAdapter
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val url = "https://www.${query.toString().ifEmpty { "google" }}.com"
                binding.searchView.clearFocus()
                binding.webView.loadUrl(url)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                reloadMatrixCursor(newText)
                return true
            }
        })
        binding.searchView.requestFocus()
        binding.webView.settings.apply {
            javaScriptEnabled = true
            loadWithOverviewMode = true
            useWideViewPort = true
        }
        binding.webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                view?.loadUrl(request?.url.toString())
                return true
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                binding.progressBar.visibility = View.VISIBLE
                binding.btnRefresh.isEnabled = false
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                binding.progressBar.visibility = View.GONE
                binding.btnBack.isEnabled = view?.canGoBack() == true
                binding.btnForward.isEnabled = view?.canGoForward() == true
                binding.btnRefresh.isEnabled = true
            }
        }
    }

    private fun reloadMatrixCursor(newText: String?) {
        val matrixCursor =
            MatrixCursor(arrayOf(BaseColumns._ID, SearchManager.SUGGEST_COLUMN_TEXT_1))
        Helper.getSiteList().forEachIndexed { index, siteName ->
            if (siteName.lowercase().contains(newText ?: "")) matrixCursor.addRow(
                arrayOf(
                    index,
                    siteName
                )
            )
        }
        cursorAdapter.changeCursor(matrixCursor)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.btnBack.id -> {
                if (binding.webView.canGoBack()) {
                    binding.webView.goBack()
                }
            }

            binding.btnForward.id -> {
                if (binding.webView.canGoForward()) {
                    binding.webView.goForward()
                }
            }

            binding.btnRefresh.id -> {
                binding.webView.reload()
            }
        }
    }
}