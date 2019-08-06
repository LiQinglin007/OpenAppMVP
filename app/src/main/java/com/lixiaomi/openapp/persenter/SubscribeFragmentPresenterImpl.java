package com.lixiaomi.openapp.persenter;

import com.lixiaomi.baselib.config.AppConfigInIt;
import com.lixiaomi.baselib.utils.MiJsonUtil;
import com.lixiaomi.baselib.utils.NetWorkUtils;
import com.lixiaomi.mvplib.base.BaseModel;
import com.lixiaomi.mvplib.base.BasePresenter;
import com.lixiaomi.mvplib.base.MyPresenterCallBack;
import com.lixiaomi.openapp.R;
import com.lixiaomi.openapp.bean.WXArticleAuthorlistBean;
import com.lixiaomi.openapp.bean.WXArticleListBean;
import com.lixiaomi.openapp.http.HttpData;
import com.lixiaomi.openapp.model.WXArticleModelImpl;
import com.lixiaomi.openapp.ui.fragment.SubscribeFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @describe：<br>
 * @author：Xiaomi<br>
 * @createTime：2019/7/31<br>
 * @remarks：<br>
 * @changeTime:<br>
 */
public class SubscribeFragmentPresenterImpl extends BasePresenter<SubscribeFragment, BaseModel> implements SubscribeFragmentPresenter {
    private SubscribeFragment mView;
    private ArrayList<WXArticleAuthorlistBean.DataBean> mAuthorList = new ArrayList<>();
    private ArrayList<WXArticleListBean.DataBean.DatasBean> mArticleList = new ArrayList<>();

    @Override
    public void getWXAuthorList() {
        mView = getView();
        if (isViewAttached()) {
            mView.startLoading();
        }

        ((WXArticleModelImpl) getModelList().get(0)).getWXArticleAuthorList(new MyPresenterCallBack() {
            @Override
            public void success(int code, String response) {
                if (isViewAttached()) {
                    mView.stopLoading();
                }
                mAuthorList.clear();
                String s = "加载失败";
                try {
                    WXArticleAuthorlistBean articleAuthorlistBean = MiJsonUtil.getClass(response, WXArticleAuthorlistBean.class);
                    if (articleAuthorlistBean.getErrorCode() == 0) {
                        List<WXArticleAuthorlistBean.DataBean> data = articleAuthorlistBean.getData();
                        if (data != null && data.size() != 0) {
                            mAuthorList.addAll(data);
                        }
                    }
                    s = articleAuthorlistBean.getErrorMsg();
                } catch (Exception e) {
                }
                mView.setAuthorListData(mAuthorList, HttpData.LOCAL_SUCCESS, s);
            }

            @Override
            public void error(String message) {
                if (isViewAttached()) {
                    mView.stopLoading();
                    mView.showToast(message);
                }
            }

            @Override
            public void failure(Throwable e) {
                if (isViewAttached()) {
                    mView.stopLoading();
                    mView.showToast(AppConfigInIt.getApplicationContext().getResources().getString(
                            NetWorkUtils.isNetworkConnected(AppConfigInIt.getApplicationContext()) ? R.string.http_onFailure : R.string.http_NoNetWorkError));
                }
            }
        });

    }

    @Override
    public void getWXArticleList(boolean showLoading, String authorId, int page) {
        mView = getView();
        if (isViewAttached()) {
            if (showLoading) {
                mView.startLoading();
            }
        }
        ((WXArticleModelImpl) getModelList().get(0)).getWXArticleListByAuthorId(authorId, page, new MyPresenterCallBack() {
            @Override
            public void success(int code, String response) {
                if (isViewAttached()) {
                    mView.stopLoading();
                }
                mArticleList.clear();
                String s = "加载失败";
                try {
                    WXArticleListBean articleListBean = MiJsonUtil.getClass(response, WXArticleListBean.class);
                    if (articleListBean.getErrorCode() == 0) {
                        List<WXArticleListBean.DataBean.DatasBean> data = articleListBean.getData().getDatas();
                        if (data != null && data.size() != 0) {
                            mArticleList.addAll(data);
                        }
                    }
                    s = articleListBean.getErrorMsg();
                    mView.setArticleListData(articleListBean.getData().getCurPage(),articleListBean.getData().getPageCount(), mArticleList, HttpData.LOCAL_SUCCESS, s);
                } catch (Exception e) {
                }

            }

            @Override
            public void error(String message) {
                if (isViewAttached()) {
                    mView.stopLoading();
                    mView.showToast(message);
                }
            }

            @Override
            public void failure(Throwable e) {
                if (isViewAttached()) {
                    mView.stopLoading();
                    mView.showToast(AppConfigInIt.getApplicationContext().getResources().getString(
                            NetWorkUtils.isNetworkConnected(AppConfigInIt.getApplicationContext()) ? R.string.http_onFailure : R.string.http_NoNetWorkError));
                }
            }
        });
    }

    @Override
    protected ArrayList<BaseModel> createModelList() {
        ArrayList<BaseModel> models = new ArrayList<>();
        models.add(new WXArticleModelImpl());
        return models;
    }
}
