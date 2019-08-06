package com.lixiaomi.openapp.ui.activity;

import com.lixiaomi.mvplib.base.BaseView;
import com.lixiaomi.openapp.bean.ArticleBean;

import java.util.ArrayList;

/**
 * @describe：<br>
 * @author：Xiaomi<br>
 * @createTime：2019/8/1<br>
 * @remarks：<br>
 * @changeTime:<br>
 */
public interface ArticleActivity extends BaseView {
    /**
     * @param pageCount
     * @param articleList
     * @param code
     * @param msg
     */
    void setArticle(int curPage,int pageCount, ArrayList<ArticleBean.DataBean.DatasBean> articleList, int code, String msg);
}
