package com.lixiaomi.openapp.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.lixiaomi.baselib.utils.T;
import com.lixiaomi.mvplib.base.BaseFragment;
import com.lixiaomi.mvplib.base.BasePresenter;
import com.lixiaomi.openapp.R;
import com.lixiaomi.openapp.bean.BaseBean;

/**
 * @describe：<br>
 * @author：Xiaomi<br>
 * @createTime：2019/7/30<br>
 * @remarks：<br>
 * @changeTime:<br>
 */
public class HomeFragmentImpl extends BaseFragment implements HomeFragment {

    public static HomeFragmentImpl getInstance() {
        return HomeFragmentImplHolder.mHomeFragmentImpl;
    }

    private static final class HomeFragmentImplHolder {
        private final static HomeFragmentImpl mHomeFragmentImpl = new HomeFragmentImpl();
    }


    @Override
    protected Object setLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View rootView, Bundle savedInstanceState) {

    }

    @Override
    protected BasePresenter createPersenter() {
        return null;
    }

    @Override
    public void setBannerData(BaseBean bannerData, int code, String msg) {

    }

    @Override
    public void showToast(String msg) {
        T.shortToast(getActivity(), msg);
    }

    @Override
    public void startLoading() {
        showLoading();
    }

    @Override
    public void stopLoading() {
        hineLoading();
    }
}
