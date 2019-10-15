package com.lixiaomi.openapp.model;

import com.lixiaomi.baselib.net.okhttp.MiSendRequestOkHttp;
import com.lixiaomi.mvplib.base.MiHttpCallBack;
import com.lixiaomi.mvplib.base.MiPersenterCallBack;
import com.lixiaomi.openapp.http.MyUrl;

import org.jetbrains.annotations.NotNull;

/**
 * @describe：<br>
 * @author：Xiaomi<br>
 * @createTime：2019/7/31<br>
 * @remarks：<br>
 * @changeTime:<br>
 */
public class WXArticleModelImpl implements WXArticleModel {

    @Override
    public void getWXArticleAuthorList(final MiPersenterCallBack myPresenterCallBack) {
        MiSendRequestOkHttp.sendGet(null, null, MyUrl.getWxArticleGet(), 120, new MiHttpCallBack(myPresenterCallBack) {
            @Override
            public void onSuccess(@NotNull String s) {
                myPresenterCallBack.success(s);
            }
        });
    }

    @Override
    public void getWXArticleListByAuthorId(String authorId, int page, final MiPersenterCallBack myPresenterCallBack) {
        MiSendRequestOkHttp.sendGet(null, null, MyUrl.getWxArticleListGet() + authorId + "/" + page + "/json", 120, new MiHttpCallBack(myPresenterCallBack) {
            @Override
            public void onSuccess(@NotNull String s) {
                myPresenterCallBack.success(s);
            }
        });
    }


}
