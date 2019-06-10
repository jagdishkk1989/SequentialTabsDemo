package com.jagdish.sequentialtabdemo.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

import com.jagdish.sequentialtabdemo.R

class ThirdFragment : Fragment() {

    public fun newInstance(): ThirdFragment {
        return ThirdFragment()
    }

    internal var flag = 0


    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            if (flag == 1) {
                loadData()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    lateinit var webView: WebView;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_second, container, false)
        flag = 1
        initUI(rootView)
        return rootView;
    }


    fun initUI(rootView: View?) {
        webView = rootView!!.findViewById(R.id.webView) as WebView
    }

    fun loadData() {
        Log.d("Logs", "Fragment Third")
        webView.loadUrl("https://www.timesnownews.com/");
        setupWebViewClient()
    }

    private fun setupWebViewClient() {
        webView.webViewClient = object : WebViewClient() {
            //            private var running = 0 // Could be public if you want a timer to check.
            override fun shouldOverrideUrlLoading(webView: WebView, urlNewString: String): Boolean {
//                running++
                webView.loadUrl(urlNewString)
                return true
            }

//            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap) {
//                running = Math.max(running, 1) // First request move it to 1.
//            }
//
//            override fun onPageFinished(view: WebView, url: String) {
//                if (--running == 0) { // just "running--;" if you add a timer.
//
//                }
//            }

            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {

            }
        }
    }

}
