package com.lixiaomi.openapp.ui.activity;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.just.agentweb.AgentWeb;
import com.lixiaomi.mvplib.base.BaseActivity;
import com.lixiaomi.mvplib.base.BasePresenter;
import com.lixiaomi.openapp.R;
import com.lixiaomi.openapp.utils.FinalData;

/**
 * @describe：banner点击过来这里，文章详情点击过来这里，来这里展示所有的<br>
 * @author：Xiaomi<br>
 * @createTime：2019/7/30<br>
 * @remarks：<br>
 * @changeTime:<br>
 */
public class WebViewActivity extends BaseActivity {
    private String mWebViewUrl;
    private android.webkit.WebView mWebView;


    @Override
    protected Object setLayout() {
        return R.layout.activity_webview;
    }

    @Override
    protected void initData() {
        mWebViewUrl = getIntent().getStringExtra(FinalData.WEB_VIEW_URL);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        mWebView = findViewById(R.id.web_view);
//        AgentWeb.with(this)
//                //传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams
//                .setAgentWebParent(mLinearLayout, new LinearLayout.LayoutParams(-1, -1))
//                // 使用默认进度条
//                .useDefaultIndicator()
//                // 使用默认进度条颜色
//                .defaultProgressBarColor()
//                //设置 Web 页面的 title 回调
//                .setReceivedTitleCallback()
//                .createAgentWeb()
//                .ready()
//                .go(mWebViewUrl);
    }

    @Override
    protected BasePresenter createPersenter() {
        return null;
    }

    @Override
    protected int setStatusBarColor() {
        return R.color.default_color;
    }
}
