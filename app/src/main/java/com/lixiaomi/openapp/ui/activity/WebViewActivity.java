package com.lixiaomi.openapp.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;


import com.lixiaomi.baselib.utils.LogUtils;
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
    private String mTitle;
    private android.webkit.WebView mWebView;


    private LinearLayoutCompat mTopLeftLy;
    private LinearLayoutCompat mTopRightLy;
    private AppCompatTextView mTopTitleTv;


    @Override
    protected Object setLayout() {
        return R.layout.activity_webview;
    }

    @Override
    protected void initData() {
        mWebViewUrl = getIntent().getStringExtra(FinalData.WEB_VIEW_URL);
        mTitle = getIntent().getStringExtra(FinalData.WEB_VIEW_TITLE);
        LogUtils.loge("mWebViewUrl:" + mWebViewUrl);
        LogUtils.loge("mTitle:" + mTitle);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mTopLeftLy = findViewById(R.id.top_back_ly);
        mTopRightLy = findViewById(R.id.top_right_ly);
        mTopTitleTv = findViewById(R.id.top_title_tv);
        mTopTitleTv.setText(mTitle);
        mTopLeftLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTopRightLy.setVisibility(View.INVISIBLE);

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
