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
public class ArticleModelImpl implements ArticleModel {

    @Override
    public void getArtcleList(int page, final MiPersenterCallBack myPresenterCallBack) {
        MiSendRequestOkHttp.sendGet(null, null, MyUrl.getArticleGet() + page + "/json", 120, new MiHttpCallBack(myPresenterCallBack) {
            @Override
            public void onSuccess(@NotNull String s) {
                myPresenterCallBack.success(s);
            }
        });
    }

    @Override
    public void getArtcleProjectList(int page, final MiPersenterCallBack myPresenterCallBack) {
        MiSendRequestOkHttp.sendGet(null, null, MyUrl.getArticleProjectGet() + page + "/json", 120, new MiHttpCallBack(myPresenterCallBack) {
            @Override
            public void onSuccess(@NotNull String s) {
                myPresenterCallBack.success(s);
            }
        });
    }
}
