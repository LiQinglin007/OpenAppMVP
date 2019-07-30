package com.lixiaomi.openapp.ui.fragment;

import com.lixiaomi.mvplib.base.BaseView;
import com.lixiaomi.openapp.bean.BannerBean;
import com.lixiaomi.openapp.bean.BaseBean;

import java.util.ArrayList;

/**
 * @describe：首页<br>
 * @author：Xiaomi<br>
 * @createTime：2019/7/30<br>
 * @remarks：<br>
 * @changeTime:<br>
 */
public interface HomeFragment extends BaseView {
    /**
     * 设置Banner
     *
     * @param mBannerList
     * @param code
     * @param msg
     */
    void setBannerData(ArrayList<BannerBean.DataBean> mBannerList, int code, String msg);

    /**
     * 设置最新项目列表
     * @param projectListBean
     * @param code
     * @param msg
     */
    void setArticleProject(BaseBean projectListBean, int code, String msg);

    /**
     *
     * @param articleListBean
     * @param code
     * @param msg
     */
    void setArticle(BaseBean articleListBean, int code, String msg);
}
