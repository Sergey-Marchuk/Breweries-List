package com.march.brewerieslist.ui.web

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import com.march.brewerieslist.R
import com.march.brewerieslist.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_web_view.*
import timber.log.Timber


class WebViewFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_web_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWebView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        val url = arguments?.getString(KEY_WEB_URL)
        Timber.e(url)
        browserWV.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        browserWV.settings.javaScriptEnabled = true
        browserWV.settings.domStorageEnabled = true
        browserWV.webViewClient = WebViewClient()
        browserWV.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, progress: Int) {
                if (progress < 100 && progressPB?.visibility == ProgressBar.GONE) {
                    progressPB?.visibility = ProgressBar.VISIBLE
                }

                progressPB?.progress = progress
                if (progress == 100) {
                    progressPB?.visibility = ProgressBar.GONE
                }
            }
        }

        browserWV.loadUrl(url)
    }

    companion object {
        private const val KEY_WEB_URL = "key_web_url"

        fun newInstance(url: String): WebViewFragment {
            val args = Bundle()
            args.putString(KEY_WEB_URL, url)
            val fragment = WebViewFragment()
            fragment.arguments = args
            return fragment
        }
    }
}