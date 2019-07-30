package com.lixiaomi.openapp.ui.fragment;

import com.lixiaomi.mvplib.base.BaseView;
import com.lixiaomi.openapp.bean.BaseBean;

/**
 * @describe：首页<br>
 * @author：Xiaomi<br>
 * @createTime：2019/7/30<br>
 * @remarks：<br>
 * @changeTime:<br>
 */
public interface HomeFragment extends BaseView {
    void setBannerData(BaseBean bannerData, int code, String msg);

    void showToast(String msg);

}
