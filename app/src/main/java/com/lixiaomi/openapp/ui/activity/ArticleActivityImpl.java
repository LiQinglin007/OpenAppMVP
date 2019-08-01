package com.lixiaomi.openapp.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lixiaomi.baselib.utils.T;
import com.lixiaomi.mvplib.base.BaseActivity;
import com.lixiaomi.mvplib.base.BasePresenter;
import com.lixiaomi.openapp.R;
import com.lixiaomi.openapp.adapter.ArticleAdapter;
import com.lixiaomi.openapp.bean.ArticleBean;
import com.lixiaomi.openapp.bean.ProjectBean;
import com.lixiaomi.openapp.bean.TreeArticleListBean;
import com.lixiaomi.openapp.persenter.activity.ArticleActivityPresenterImpl;
import com.lixiaomi.openapp.utils.FinalData;

import java.util.ArrayList;

/**
 * @describe：文章列表<br>
 * @author：Xiaomi<br>
 * @createTime：2019/8/1<br>
 * @remarks：<br>
 * @changeTime:<br>
 */
public class ArticleActivityImpl extends BaseActivity<ArticleActivity, ArticleActivityPresenterImpl> implements ArticleActivity {
    private LinearLayoutCompat mTopLeftLy;
    private LinearLayoutCompat mTopRightLy;
    private AppCompatTextView mTopTitleTv;

    private android.support.v4.widget.SwipeRefreshLayout mArticleRefresh;
    private android.support.v7.widget.RecyclerView mArticleRecy;

    private ArticleAdapter mAdapter;
    private ArrayList<ArticleBean.DataBean.DatasBean> mDataList = new ArrayList();
    private int mPage = 1;
    private boolean mLoadMoreIng = false;
    private boolean mRefreshIng = false;

    @Override
    protected Object setLayout() {
        return R.layout.activity_article_list;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mTopLeftLy = findViewById(R.id.top_back_ly);
        mTopRightLy = findViewById(R.id.top_right_ly);
        mTopTitleTv = findViewById(R.id.top_title_tv);
        mTopTitleTv.setText("最新文章");
        mTopLeftLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTopRightLy.setVisibility(View.INVISIBLE);
        mArticleRefresh = findViewById(R.id.article_refresh);
        mArticleRecy = findViewById(R.id.article_recy);

        mArticleRecy.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ArticleAdapter(R.layout.item_article);
        mAdapter.setNewData(mDataList);
        mArticleRecy.setAdapter(mAdapter);

        mArticleRefresh.setEnabled(true);
        mArticleRefresh.setColorSchemeColors(
                getResources().getColor(R.color.color_51D8BA),
                getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.default_color));
        // 设置手指在屏幕下拉多少距离会触发下拉刷新
        mArticleRefresh.setDistanceToTriggerSync(300);
        // 设定下拉圆圈的背景
        mArticleRefresh.setProgressBackgroundColorSchemeColor(Color.WHITE);
        // 设置圆圈的大小
        mArticleRefresh.setSize(SwipeRefreshLayout.LARGE);
        mArticleRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!mLoadMoreIng && !mRefreshIng) {
                    mRefreshIng = true;
                    mPage = 1;
                    //下拉刷新的时候不让上拉加载
                    mAdapter.setEnableLoadMore(false);
                    getData(false);
                }
            }
        });

        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (!mLoadMoreIng && !mRefreshIng) {
                    mLoadMoreIng = true;
                    ++mPage;
                    //上拉加载的时候不让下拉刷新
                    mArticleRefresh.setEnabled(false);
                    getData(false);
                }
            }
        }, mArticleRecy);


        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(ArticleActivityImpl.this, WebViewActivity.class)
                        .putExtra(FinalData.WEB_VIEW_URL, mDataList.get(position).getLink())
                        .putExtra(FinalData.WEB_VIEW_TITLE, mDataList.get(position).getTitle())
                );
            }
        });
        getData(true);
    }

    private void getData(boolean showLoading) {
        mPersenter.getArticleData(showLoading, mPage);
    }

    @Override
    public void setArticle(int pageCount, ArrayList<ArticleBean.DataBean.DatasBean> articleList, int code, String msg) {
        if (mPage == 1) {
            mDataList.clear();
        }
        mDataList.addAll(articleList);
        mAdapter.replaceData(mDataList);
        mArticleRefresh.setEnabled(true);
        mArticleRefresh.setRefreshing(false);
        mAdapter.setEnableLoadMore(true);
        mLoadMoreIng = false;
        mRefreshIng = false;
        if (mPage >= pageCount) {
            mAdapter.loadMoreEnd();
        } else {
            mAdapter.loadMoreComplete();
        }
    }

    @Override
    protected ArticleActivityPresenterImpl createPersenter() {
        return new ArticleActivityPresenterImpl();
    }

    @Override
    protected int setStatusBarColor() {
        return R.color.default_color;
    }


    @Override
    public void startLoading() {
        showLoading();
    }

    @Override
    public void stopLoading() {
        hineLoading();
    }

    @Override
    public void showToast(String msg) {
        T.shortToast(this, msg);
    }


}
