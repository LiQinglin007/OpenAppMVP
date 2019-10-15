package com.lixiaomi.openapp.ui.activity;

import com.lixiaomi.mvplib.base.BaseView;
import com.lixiaomi.openapp.bean.ArticleBean;
import com.lixiaomi.openapp.bean.ProjectBean;

import java.util.ArrayList;

/**
 * @describe：<br>
 * @author：Xiaomi<br>
 * @createTime：2019/8/1<br>
 * @remarks：<br>
 * @changeTime:<br>
 */
public interface ProjectActivity extends BaseView {
    /**
     * 设置最新项目列表
     *
     * @param pageCount
     * @param projectList
     * @param code
     * @param msg
     */
    void setArticleProject(int curPage,int pageCount, ArrayList<ProjectBean.DataBean.DatasBean> projectList);

}
