package com.lixiaomi.openapp.application;

import android.app.Activity;
import android.app.Application;

import com.lixiaomi.baselib.config.AppConfigInIt;
import com.lixiaomi.baselib.net.HttpConfig;
import com.tencent.smtt.sdk.QbSdk;

import java.io.InputStream;
import java.util.ArrayList;

import okhttp3.Interceptor;

/**
 * 作者：dell or Xiaomi Li
 * 时间： 2018/3/31
 * 内容：
 * 最后修改：
 */

public class MyApplication extends Application {
    private final String SharedPreferences = "OpenAppMVP";

    @Override
    public void onCreate() {
        super.onCreate();
        HttpConfig httpConfig = new HttpConfig("https://www.wanandroid.com/", null, null, true, null,
                true, 401, "请求超时，请重新登陆", 200, 0, "<html>", null);
        AppConfigInIt.init(this)
                //设置调试模式，默认false
                .withDebug(true)
                //配置SharedPreferences
                .withSharedPreferences(getSharedPreferences(SharedPreferences, Activity.MODE_PRIVATE))
                //默认文件根地址
                .withBaseFile("com.lixiaomi.openappmvp")
                .withHttpConfig(httpConfig)
                .configure();
        QbSdk.initX5Environment(this, null);
    }
}
