package com.lixiaomi.openapp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.lixiaomi.baselib.utils.T;
import com.lixiaomi.mvplib.base.BaseFragment;
import com.lixiaomi.openapp.R;
import com.lixiaomi.openapp.adapter.SystemFragmentAdapter;
import com.lixiaomi.openapp.bean.MyTreeBean;
import com.lixiaomi.openapp.persenter.SystemFragmentPresenterImpl;
import com.lixiaomi.openapp.ui.activity.SystemListActivityImpl;
import com.lixiaomi.openapp.utils.FinalData;

import java.util.ArrayList;

/**
 * @describe：<br>
 * @author：Xiaomi<br>
 * @createTime：2019/7/30<br>
 * @remarks：<br>
 * @changeTime:<br>
 */
public class SystemFragmentImpl extends BaseFragment<SystemFragment, SystemFragmentPresenterImpl> implements SystemFragment {


    private android.support.v7.widget.RecyclerView mSystemRecy;
    private SystemFragmentAdapter mSystemFragmentAdapter;
    private ArrayList<MultiItemEntity> mListData = new ArrayList<>();

    private LinearLayoutCompat mTopLeftLy;
    private LinearLayoutCompat mTopRightLy;
    private AppCompatTextView mTopTitleTv;

    public static SystemFragmentImpl getInstance() {
        return SystemFragmentImplHolder.mSystemFragmentImpl;
    }


    private static final class SystemFragmentImplHolder {
        private final static SystemFragmentImpl mSystemFragmentImpl = new SystemFragmentImpl();
    }


    @Override
    protected Object setLayout() {
        return R.layout.fragment_system;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View rootView, Bundle savedInstanceState) {

        mTopLeftLy = rootView.findViewById(R.id.top_back_ly);
        mTopRightLy = rootView.findViewById(R.id.top_right_ly);
        mTopTitleTv = rootView.findViewById(R.id.top_title_tv);

        mTopTitleTv.setText("体系");
        mTopLeftLy.setVisibility(View.INVISIBLE);
        mTopRightLy.setVisibility(View.INVISIBLE);

        mSystemRecy = rootView.findViewById(R.id.system_recy);
        mSystemRecy.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSystemFragmentAdapter = new SystemFragmentAdapter(mListData);
        mSystemRecy.setAdapter(mSystemFragmentAdapter);
        mPersenter.getTreeTypeList();

        mSystemFragmentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (adapter.getItem(position) instanceof MyTreeBean.ChildrenBean) {
                    startActivity(new Intent(getActivity(), SystemListActivityImpl.class)
                            .putExtra(FinalData.SYSTEM_TYPE_ID, ((MyTreeBean.ChildrenBean) adapter.getItem(position)).getId())
                            .putExtra(FinalData.SYSTEM_TYPE_TITLE, ((MyTreeBean.ChildrenBean) adapter.getItem(position)).getName())
                    );
                }
            }
        });
    }


    @Override
    public void setTreeData(ArrayList<MyTreeBean> treeList) {
        mListData.clear();
        mListData.addAll(treeList);
        mSystemFragmentAdapter.replaceData(mListData);
    }


    @Override
    protected SystemFragmentPresenterImpl createPersenter() {
        return new SystemFragmentPresenterImpl();
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
