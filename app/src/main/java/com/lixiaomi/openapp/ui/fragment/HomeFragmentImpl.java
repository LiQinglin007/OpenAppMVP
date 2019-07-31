package com.lixiaomi.openapp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lixiaomi.baselib.utils.LogUtils;
import com.lixiaomi.baselib.utils.MiJsonUtil;
import com.lixiaomi.baselib.utils.T;
import com.lixiaomi.mvplib.base.BaseFragment;
import com.lixiaomi.openapp.R;
import com.lixiaomi.openapp.adapter.ArticleAdapter;
import com.lixiaomi.openapp.adapter.BannerHolderView;
import com.lixiaomi.openapp.adapter.ProjectAdapter;
import com.lixiaomi.openapp.bean.ArticleBean;
import com.lixiaomi.openapp.bean.BannerBean;
import com.lixiaomi.openapp.bean.ProjectBean;
import com.lixiaomi.openapp.http.HttpData;
import com.lixiaomi.openapp.persenter.HomeFragmentPresenterImpl;
import com.lixiaomi.openapp.ui.activity.WebViewActivity;
import com.lixiaomi.openapp.utils.FinalData;

import java.util.ArrayList;

/**
 * @describe：<br>
 * @author：Xiaomi<br>
 * @createTime：2019/7/30<br>
 * @remarks：<br>
 * @changeTime:<br>
 */
public class HomeFragmentImpl extends BaseFragment<HomeFragment, HomeFragmentPresenterImpl> implements HomeFragment {

    private com.bigkoo.convenientbanner.ConvenientBanner mHomeBanner;
    private RecyclerView mHomeRecyArticle;
    private RecyclerView mHomeRecyProject;
    private ArticleAdapter mArticleAdapter;
    private ProjectAdapter mProjectAdapter;

    public static HomeFragmentImpl getInstance() {
        return HomeFragmentImplHolder.mHomeFragmentImpl;
    }

    private static final class HomeFragmentImplHolder {
        private final static HomeFragmentImpl mHomeFragmentImpl = new HomeFragmentImpl();
    }

    ArrayList<BannerBean.DataBean> mDataList = new ArrayList<>();
    ArrayList<ArticleBean.DataBean.DatasBean> mArticleDataList = new ArrayList<>();
    ArrayList<ProjectBean.DataBean.DatasBean> mProjectDataList = new ArrayList<>();

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
        mHomeRecyArticle = rootView.findViewById(R.id.home_recy_article);
        mHomeRecyProject = rootView.findViewById(R.id.home_recy_project);


        mArticleAdapter = new ArticleAdapter(R.layout.item_article);
        mHomeRecyArticle.setLayoutManager(new LinearLayoutManager(getActivity()));
        mHomeRecyArticle.setAdapter(mArticleAdapter);
        View articleHeadView = LayoutInflater.from(getActivity()).inflate(R.layout.head_home_article, mHomeRecyArticle, false);
        mArticleAdapter.addHeaderView(articleHeadView);

        mProjectAdapter = new ProjectAdapter(R.layout.item_project);
        mHomeRecyProject.setLayoutManager(new LinearLayoutManager(getActivity()));
        mHomeRecyProject.setAdapter(mProjectAdapter);
        View projectHeadView = LayoutInflater.from(getActivity()).inflate(R.layout.head_home_project, mHomeRecyProject, false);
        mProjectAdapter.addHeaderView(projectHeadView);

        //加载图片
        mPersenter.getBannerData();
        mPersenter.getHomeArticle();
        mPersenter.getHomeArticleProject();


        mArticleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(getActivity(), WebViewActivity.class)
                        .putExtra(FinalData.WEB_VIEW_URL, mArticleDataList.get(position).getLink()));
            }
        });

        mProjectAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(getActivity(), WebViewActivity.class)
                        .putExtra(FinalData.WEB_VIEW_URL, mProjectDataList.get(position).getLink()));
            }
        });


    }

    @Override
    public void setBannerData(ArrayList<BannerBean.DataBean> bannerList, int code, String msg) {
        mDataList.clear();
        if (code == HttpData.LOCAL_SUCCESS) {
            mDataList.addAll(bannerList);
            //给Banner设置图片
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
                            startActivity(new Intent(getActivity(), WebViewActivity.class)
                                    .putExtra(FinalData.WEB_VIEW_URL, mDataList.get(position).getUrl()));
                        }
                    })
                    .setPageIndicator(new int[]{R.drawable.dot_focus, R.drawable.dot_normal})
                    .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
        } else {
            T.shortToast(getActivity(), msg);
        }
    }

    @Override
    public void setArticleProject(ArrayList<ProjectBean.DataBean.DatasBean> projectList, int code, String msg) {
        mProjectDataList.clear();
        if (code == HttpData.LOCAL_SUCCESS) {
            mProjectDataList.addAll(projectList);
            LogUtils.loge("mProjectDataList" + MiJsonUtil.getJson(mProjectDataList));
        } else {
            T.shortToast(getActivity(), msg);
        }
        mProjectAdapter.setNewData(mProjectDataList);
    }

    @Override
    public void setArticle(ArrayList<ArticleBean.DataBean.DatasBean> articleList, int code, String msg) {
        mArticleDataList.clear();
        if (code == HttpData.LOCAL_SUCCESS) {
            mArticleDataList.addAll(articleList);
            LogUtils.loge("mArticleDataList" + MiJsonUtil.getJson(mArticleDataList));
        } else {
            T.shortToast(getActivity(), msg);
        }
        mArticleAdapter.setNewData(mArticleDataList);
    }


    @Override
    protected HomeFragmentPresenterImpl createPersenter() {
        return new HomeFragmentPresenterImpl();
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
