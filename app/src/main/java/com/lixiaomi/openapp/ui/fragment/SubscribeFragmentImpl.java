package com.lixiaomi.openapp.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.lixiaomi.baselib.utils.T;
import com.lixiaomi.mvplib.base.BaseFragment;
import com.lixiaomi.mvplib.base.BasePresenter;
import com.lixiaomi.openapp.R;

/**
 * @describe：<br>
 * @author：Xiaomi<br>
 * @createTime：2019/7/30<br>
 * @remarks：<br>
 * @changeTime:<br>
 */
public class SubscribeFragmentImpl extends BaseFragment implements SubscribeFragment {


    public static SubscribeFragmentImpl getInstance() {
        return SubscribeFragmentImplHolder.mSubscribeFragmentImpl;
    }

    private static final class SubscribeFragmentImplHolder {
        private final static SubscribeFragmentImpl mSubscribeFragmentImpl = new SubscribeFragmentImpl();
    }


    @Override
    protected Object setLayout() {
        return R.layout.fragment_subscribe;
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
