package com.qoiutestapp.chuckjokes.presentation.ui.web

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.qoiutestapp.chuckjokes.databinding.FragmentWebBinding
import com.qoiutestapp.chuckjokes.presentation.BaseFragment
import com.qoiutestapp.chuckjokes.presentation.FragmentBackPress

class WebFragment : BaseFragment<FragmentWebBinding>(), FragmentBackPress {

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentWebBinding =
        FragmentWebBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.webView.webViewClient = MyWebViewClient()
        savedInstanceState ?: binding.webView.loadUrl(BASE_URL)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.webView.saveState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.let {
            binding.webView.restoreState(it)
        }
    }

    inner class MyWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            binding.webView.loadUrl(request?.url.toString())
            return true
        }
    }

    override fun onBackPress(): Boolean {
        val webView = binding.webView
        if (webView.canGoBack()) {
            webView.goBack()
            return false
        }
        return true
    }

    private companion object {
        const val BASE_URL = "http://www.icndb.com/api/"
    }
}