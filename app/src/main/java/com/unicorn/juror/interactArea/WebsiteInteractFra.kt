package com.unicorn.juror.interactArea


import android.annotation.SuppressLint
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.unicorn.juror.R
import com.unicorn.juror.app.BaseFra
import kotlinx.android.synthetic.main.fra_website_interact.*

/*
庭审直播http://www.sxgaofa.cn/ts/index
公众服务网http://www.sxgaofa.cn
 */

class WebsiteInteractFra : BaseFra() {

    override val layoutID = R.layout.fra_website_interact

    override fun initViews() {
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun bindIntent() {
//        webView.setWebChromeClient(WebChromeClient())
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return false
            }
        }
        webView.settings.javaScriptEnabled = true
        webView.loadUrl("https://xa.fang.lianjia.com/?utm_source=baidu_xf&utm_medium=pinzhuan&utm_term=biaoti&utm_content=biaotimiaoshu&utm_campaign=sousuo")
    }

    override fun onBackPressedSupport(): Boolean {
        if (webView.canGoBack()) {
            webView.goBack()
            return true;
        }
        return super.onBackPressedSupport()
    }

}
