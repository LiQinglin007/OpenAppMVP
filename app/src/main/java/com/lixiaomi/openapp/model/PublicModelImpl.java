package com.lixiaomi.openapp.model;

import com.lixiaomi.baselib.net.okhttp.MiSendRequestOkHttp;
import com.lixiaomi.mvplib.base.MiHttpCallBack;
import com.lixiaomi.mvplib.base.MiPersenterCallBack;
import com.lixiaomi.openapp.http.MyUrl;

import org.jetbrains.annotations.NotNull;

/**
 * @describe：<br>
 * @author：Xiaomi<br>
 * @createTime：2019/7/30<br>
 * @remarks：<br>
 * @changeTime:<br>
 */
public class PublicModelImpl implements PublicModel {

    @Override
    public void getBannerList(final MiPersenterCallBack myPresenterCallBack) {

        MiSendRequestOkHttp.sendGet(null, null, MyUrl.getBannerGet(), 60 * 10, new MiHttpCallBack(myPresenterCallBack) {
            @Override
            public void onSuccess(@NotNull String s) {
                myPresenterCallBack.success(s);
            }
        });
    }
}
