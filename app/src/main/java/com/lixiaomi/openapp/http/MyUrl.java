package com.lixiaomi.openapp.http;

/**
 * @describe：<br>
 * @author：Xiaomi<br>
 * @createTime：2019/7/30<br>
 * @remarks：<br>
 * @changeTime:<br>
 */
public class MyUrl {
    /**
     * 首页Banner
     */
    private static final String BANNER_GET = "banner/json";
    //    article/list/1/json
    /**
     * 首页文章  可分页
     */
    private static final String ARTICLE_GET = "article/list/";
    /**
     * 首页最新项目
     */
    private static final String ARTICLE_PROJECT_GET = "article/listproject/";

    public static String getBannerGet() {
        return BANNER_GET;
    }

    public static String getArticleGet() {
        return ARTICLE_GET;
    }

    public static String getArticleProjectGet() {
        return ARTICLE_PROJECT_GET;
    }
}
