package com.lixiaomi.openapp.ui.activity;

import com.lixiaomi.mvplib.base.BaseView;
import com.lixiaomi.openapp.bean.TreeArticleListBean;

import java.util.ArrayList;

/**
 * @describe：<br>
 * @author：Xiaomi<br>
 * @createTime：2019/8/1<br>
 * @remarks：<br>
 * @changeTime:<br>
 */
public interface SystemListActivity extends BaseView {
    void setSystemArticleList(int curPage,int page, ArrayList<TreeArticleListBean.DataBean.DatasBean> list);
}
