package com.lixiaomi.openapp.model;

import com.lixiaomi.mvplib.base.BaseModel;
import com.lixiaomi.mvplib.base.MiPersenterCallBack;

/**
 * @describe：公共模块<br>
 * @author：Xiaomi<br>
 * @createTime：2019/7/30<br>
 * @remarks：<br>
 * @changeTime:<br>
 */
public interface PublicModel  extends BaseModel {
    /**
     * 获取首页轮播图列表
     */
    void getBannerList(MiPersenterCallBack myPresenterCallBack);

}
