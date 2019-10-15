package com.lixiaomi.openapp.model;

import com.lixiaomi.mvplib.base.BaseModel;
import com.lixiaomi.mvplib.base.MiPersenterCallBack;

/**
 * @describe：<br>
 * @author：Xiaomi<br>
 * @createTime：2019/7/30<br>
 * @remarks：<br>
 * @changeTime:<br>
 */
public interface ArticleModel  extends BaseModel {
    /**
     * 获取文章列表 可分页
     *
     * @param page                页码
     * @param MiPersenterCallBack
     */
    void getArtcleList(int page, MiPersenterCallBack MiPersenterCallBack);

    /**
     * 获取首页最新项目  可分页
     *
     * @param page                页码
     * @param MiPersenterCallBack
     */
    void getArtcleProjectList(int page, MiPersenterCallBack MiPersenterCallBack);
}
