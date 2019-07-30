package com.lixiaomi.openapp.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bigkoo.convenientbanner.listener.OnPageChangeListener;
import com.lixiaomi.baselib.utils.LogUtils;
import com.lixiaomi.baselib.utils.T;
import com.lixiaomi.mvplib.base.BaseFragment;
import com.lixiaomi.mvplib.base.BasePresenter;
import com.lixiaomi.openapp.R;
import com.lixiaomi.openapp.adapter.BannerHolderView;
import com.lixiaomi.openapp.bean.BannerBean;
import com.lixiaomi.openapp.bean.BaseBean;
import com.lixiaomi.openapp.http.HttpData;
import com.lixiaomi.openapp.persenter.HomeFrPersenterImpl;

import java.util.ArrayList;

/**
 * @describe：<br>
 * @author：Xiaomi<br>
 * @createTime：2019/7/30<br>
 * @remarks：<br>
 * @changeTime:<br>
 */
public class HomeFragmentImpl extends BaseFragment<HomeFragment, HomeFrPersenterImpl> implements HomeFragment {

    private com.bigkoo.convenientbanner.ConvenientBanner mHomeBanner;
    private android.support.v7.widget.RecyclerView mHomeRecy;

    public static HomeFragmentImpl getInstance() {
        return HomeFragmentImplHolder.mHomeFragmentImpl;
    }

    private static final class HomeFragmentImplHolder {
        private final static HomeFragmentImpl mHomeFragmentImpl = new HomeFragmentImpl();
    }

    ArrayList<BannerBean.DataBean> mDataList = new ArrayList<>();

    @Override
    protected Object setLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View rootView, Bundle savedInstanceState) {

        mHomeBanner = rootView.findViewById(R.id.home_banner);
        mHomeRecy = rootView.findViewById(R.id.home_recy);

        mPersenter.getBannerData();
    }


    @Override
    public void setBannerData(ArrayList<BannerBean.DataBean> bannerList, int code, String msg) {
        mDataList.clear();
        if (code == HttpData.LOCAL_SUCCESS) {
            mDataList.addAll(bannerList);
            //本地图片例子
            mHomeBanner.setPages(
                    new CBViewHolderCreator() {
                        @Override
                        public Holder createHolder(View itemView) {
                            return new BannerHolderView(itemView);
                        }

                        @Override
                        public int getLayoutId() {
                            return R.layout.item_localimage;
                        }
                    }, mDataList)
                    .setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            LogUtils.loge("点击第" + position + "个");
                        }
                    })
                    //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                    .setPageIndicator(new int[]{R.drawable.dot_focus, R.drawable.dot_normal})
                    //设置指示器的方向--中间
                    .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
        } else {
            T.shortToast(getActivity(), msg);
        }
    }

    @Override
    public void setArticleProject(BaseBean projectListBean, int code, String msg) {

    }

    @Override
    public void setArticle(BaseBean articleListBean, int code, String msg) {

    }

    @Override
    protected HomeFrPersenterImpl createPersenter() {
        return new HomeFrPersenterImpl();
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
