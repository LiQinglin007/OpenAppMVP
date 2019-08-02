package com.lixiaomi.openapp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lixiaomi.baselib.utils.T;
import com.lixiaomi.baselib.utils.loadImageUtils.MiLoadImageUtil;
import com.lixiaomi.mvplib.base.BaseFragment;
import com.lixiaomi.mvplib.base.BasePresenter;
import com.lixiaomi.openapp.R;
import com.lixiaomi.openapp.ui.activity.UtilsActivity;

/**
 * @describe：<br>
 * @author：Xiaomi<br>
 * @createTime：2019/7/30<br>
 * @remarks：<br>
 * @changeTime:<br>
 */
public class MeFragmentImpl extends BaseFragment implements MeFragment {


    private android.support.v7.widget.AppCompatImageView mMineTakePic;
    private android.support.v7.widget.AppCompatTextView mMineUtils;

    public static MeFragmentImpl getInstance() {
        return MeFragmentImplHolder.mMeFragmentImpl;
    }

    private static final class MeFragmentImplHolder {
        private final static MeFragmentImpl mMeFragmentImpl = new MeFragmentImpl();
    }


    @Override
    protected Object setLayout() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View rootView, Bundle savedInstanceState) {

        mMineTakePic = rootView.findViewById(R.id.mine_take_pic);
        mMineUtils = rootView.findViewById(R.id.mine_utils);

        MiLoadImageUtil.loadImageCircle(getActivity(), R.drawable.headview, mMineTakePic);
        mMineUtils.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), UtilsActivity.class));
            }
        });

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
