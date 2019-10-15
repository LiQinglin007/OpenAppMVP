package com.lixiaomi.openapp.model;

import com.lixiaomi.baselib.net.okhttp.MiSendRequestOkHttp;
import com.lixiaomi.mvplib.base.MiHttpCallBack;
import com.lixiaomi.mvplib.base.MiPersenterCallBack;
import com.lixiaomi.openapp.http.MyUrl;

import org.jetbrains.annotations.NotNull;

import java.util.WeakHashMap;

/**
 * @describe：<br>
 * @author：Xiaomi<br>
 * @createTime：2019/8/1<br>
 * @remarks：<br>
 * @changeTime:<br>
 */
public class TreeModelmpl implements TreeModel {

    @Override
    public void getTreeTypeList(final MiPersenterCallBack myPresenterCallBack) {
        MiSendRequestOkHttp.sendGet(null, null, MyUrl.getTreeGet(), 120, new MiHttpCallBack(myPresenterCallBack) {
            @Override
            public void onSuccess(@NotNull String s) {
                myPresenterCallBack.success(s);
            }
        });
    }

    @Override
    public void getTreeList(int page, String cId, final MiPersenterCallBack myPresenterCallBack) {
        WeakHashMap<String, Object> para = new WeakHashMap<>(1);
        para.put("cid", cId);
        MiSendRequestOkHttp.sendGet(null, para, MyUrl.getTreeListGet() + page + "/json", 120, new MiHttpCallBack(myPresenterCallBack) {
            @Override
            public void onSuccess(@NotNull String s) {
                myPresenterCallBack.success(s);
            }
        });
    }
}
