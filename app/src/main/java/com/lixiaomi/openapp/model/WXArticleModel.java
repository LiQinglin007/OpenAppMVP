package com.lixiaomi.openapp.model;

import com.lixiaomi.mvplib.base.BaseModel;
import com.lixiaomi.mvplib.base.MyPresenterCallBack;

/**
 * @describe：<br>
 * @author：Xiaomi<br>
 * @createTime：2019/7/31<br>
 * @remarks：<br>
 * @changeTime:<br>
 */
public interface WXArticleModel extends BaseModel {

    /**
     * 获取公众号作者列表
     *
     * @param myPresenterCallBack
     */
    void getWXArticleAuthorList(MyPresenterCallBack myPresenterCallBack);

    /**
     * 获取某个公众号下的文章列表
     *
     * @param authorId            公众号id
     * @param page                页面
     * @param myPresenterCallBack
     */
    void getWXArticleListByAuthorId(String authorId, int page, MyPresenterCallBack myPresenterCallBack);

}
