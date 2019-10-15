package com.lixiaomi.openapp.ui.fragment;

import com.lixiaomi.mvplib.base.BaseView;
import com.lixiaomi.openapp.bean.WXArticleAuthorlistBean;
import com.lixiaomi.openapp.bean.WXArticleListBean;

import java.util.ArrayList;

/**
 * @describe：<br>
 * @author：Xiaomi<br>
 * @createTime：2019/7/30<br>
 * @remarks：<br>
 * @changeTime:<br>
 */
public interface SubscribeFragment extends BaseView {

    /**
     * 公众号列表
     *
     * @param authorListData
     * @param code
     * @param msg
     */
    void setAuthorListData(ArrayList<WXArticleAuthorlistBean.DataBean> authorListData);

    /**
     * 某个公众号下的文章列表
     *
     * @param articleListData
     * @param code
     * @param msg
     */
    void setArticleListData(int curPage,int pageCount, ArrayList<WXArticleListBean.DataBean.DatasBean> articleListData);
}