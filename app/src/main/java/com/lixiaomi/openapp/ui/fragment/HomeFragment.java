package com.lixiaomi.openapp.ui.fragment;

import com.lixiaomi.mvplib.base.BaseView;
import com.lixiaomi.openapp.bean.ArticleBean;
import com.lixiaomi.openapp.bean.BannerBean;
import com.lixiaomi.openapp.bean.BaseBean;
import com.lixiaomi.openapp.bean.ProjectBean;

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
     */
    void setBannerData(ArrayList<BannerBean.DataBean> mBannerList);

    /**
     * 设置最新项目列表
     *
     * @param projectList
     */
    void setArticleProject(ArrayList<ProjectBean.DataBean.DatasBean> projectList);

    /**
     * @param articleList
     */
    void setArticle(ArrayList<ArticleBean.DataBean.DatasBean> articleList);
}
