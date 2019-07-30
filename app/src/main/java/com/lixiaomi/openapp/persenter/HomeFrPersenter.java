package com.lixiaomi.openapp.persenter;

/**
 * @describe：<br>
 * @author：Xiaomi<br>
 * @createTime：2019/7/30<br>
 * @remarks：<br>
 * @changeTime:<br>
 */
public interface HomeFrPersenter {
    /**
     * 获取首页Banner
     */
    void getBannerData();

    /**
     * 获取首页文章列表
     */
    void getHomeArticle();

    /**
     * 获取首页最新项目列表
     */
    void getHomeArticleProject();

}
