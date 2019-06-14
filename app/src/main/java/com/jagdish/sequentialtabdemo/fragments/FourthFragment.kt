package com.jagdish.sequentialtabdemo.fragments

import android.content.Context
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
import android.widget.ProgressBar
import com.jagdish.sequentialtabdemo.MainActivity
import com.jagdish.sequentialtabdemo.R
import com.jagdish.sequentialtabdemo.utility.ConnectionDetector
import com.jagdish.sequentialtabdemo.utility.Utility

class FourthFragment : Fragment() {

    public fun newInstance(): FourthFragment {
        return FourthFragment()
    }

    lateinit var activityContext: MainActivity;
    lateinit var connectionDetector: ConnectionDetector
    internal var isViewInitialized = false

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            if (isViewInitialized) {
                if (connectionDetector.isConnectedToInternet) {
                    showProgressBar()
                    loadData()
                } else {
                    hideProgressBar()
                    Utility.showAlertDialog(activityContext, activityContext.resources.getString(R.string.title_no_internet), activityContext.resources.getString(R.string.message_no_internet))
                }
            }
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (activity != null) {
            activityContext = activity as MainActivity
            connectionDetector = ConnectionDetector(activityContext)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    lateinit var webView: WebView;
    lateinit var progressBar: ProgressBar;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_webview, container, false)
        initUI(rootView)
        return rootView;
    }


    fun initUI(rootView: View?) {
        webView = rootView!!.findViewById(R.id.webView) as WebView
        progressBar = rootView!!.findViewById(R.id.progressBar) as ProgressBar

        isViewInitialized = true
    }

    fun loadData() {
        webView.loadUrl("https://www.bing.com/");
        setupWebViewClient()
    }

    private fun setupWebViewClient() {
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(webView: WebView, urlNewString: String): Boolean {
                webView.loadUrl(urlNewString)
                return true
            }

            override fun onPageFinished(view: WebView, url: String) {
                Log.d("FourthFragment", "jk Page loading finished");
                hideProgressBar();
                if (activityContext.autoSelected)
                    activityContext.loadNextPage()
            }

            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                hideProgressBar();
                Utility.showAlertDialog(activityContext, resources.getString(R.string.title_error_loading_data), resources.getString(R.string.message_no_internet))
            }
        }
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

}
