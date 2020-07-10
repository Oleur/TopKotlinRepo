package com.js.topkotlinrepo.ui.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.js.topkotlinrepo.BuildConfig
import com.js.topkotlinrepo.R
import com.js.topkotlinrepo.databinding.FragmentAuthBinding
import org.koin.android.viewmodel.ext.android.sharedViewModel

class AuthFragment : Fragment() {

    private val viewModel by sharedViewModel<LoginViewModel>()

    private lateinit var binding: FragmentAuthBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_auth, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(webView: WebView, request: WebResourceRequest): Boolean {
                val url = request.url.toString()
                if (!url.contains("?code=")) {
                    return false
                }

                val code = url.substring(url.lastIndexOf("?code=") + 1).split("=".toRegex())[1].split("&".toRegex())[0]
                viewModel.authenticate(code)
                return false
            }
        }
        val authUrl = "${BuildConfig.GITHUB_AUTH_ENDPOINT}?client_id=${BuildConfig.GITHUB_CLIENT_ID}"
        binding.webView.loadUrl(authUrl)
    }

    override fun onDestroyView() {
        binding.unbind()
        super.onDestroyView()
    }
}
