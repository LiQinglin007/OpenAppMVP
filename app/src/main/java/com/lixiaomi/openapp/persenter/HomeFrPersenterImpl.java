package com.lixiaomi.openapp.persenter;

import com.lixiaomi.baselib.config.AppConfigInIt;
import com.lixiaomi.baselib.utils.MiJsonUtil;
import com.lixiaomi.baselib.utils.NetWorkUtils;
import com.lixiaomi.baselib.utils.T;
import com.lixiaomi.mvplib.base.BaseModel;
import com.lixiaomi.mvplib.base.BasePresenter;
import com.lixiaomi.mvplib.base.MyPresenterCallBack;
import com.lixiaomi.openapp.R;
import com.lixiaomi.openapp.bean.BannerBean;
import com.lixiaomi.openapp.http.HttpData;
import com.lixiaomi.openapp.model.ArticleModelImpl;
import com.lixiaomi.openapp.model.PublicModelImpl;
import com.lixiaomi.openapp.ui.fragment.HomeFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @describe：<br>
 * @author：Xiaomi<br>
 * @createTime：2019/7/30<br>
 * @remarks：<br>
 * @changeTime:<br>
 */
public class HomeFrPersenterImpl extends BasePresenter<HomeFragment, BaseModel> implements HomeFrPersenter {
    private HomeFragment mView;

    private ArrayList<BannerBean.DataBean> mBannerList = new ArrayList<>();

    @Override
    protected ArrayList<BaseModel> createModelList() {
        ArrayList<BaseModel> modelList = new ArrayList<>();
        modelList.add(new PublicModelImpl());
        modelList.add(new ArticleModelImpl());
        return modelList;
    }

    @Override
    public void getBannerData() {
        mView = getView();
        if (isViewAttached()) {
            mView.startLoading();
        }
        ((PublicModelImpl) getModelList().get(0)).getBannerList(new MyPresenterCallBack() {
            @Override
            public void success(int code, String response) {
                mView.stopLoading();
                mBannerList.clear();
                String s = "加载失败";
                try {
                    BannerBean roomListBean = MiJsonUtil.getClass(response, BannerBean.class);
                    if (roomListBean.getErrorCode() == 0) {
                        List<BannerBean.DataBean> data = roomListBean.getData();
                        if (data != null && data.size() != 0) {
                            mBannerList.addAll(data);
                        }
                    }
                    s = roomListBean.getErrorMsg();
                } catch (Exception e) {
                }
                mView.setBannerData(mBannerList, HttpData.LOCAL_SUCCESS, s);
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
    public void getHomeArticle() {
        mView = getView();
        if (isViewAttached()) {
            mView.startLoading();
        }
        ((ArticleModelImpl) getModelList().get(0)).getArtcleList(0, new MyPresenterCallBack() {
            @Override
            public void success(int code, String response) {

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
    public void getHomeArticleProject() {
        mView = getView();
        if (isViewAttached()) {
            mView.startLoading();
        }
        ((ArticleModelImpl) getModelList().get(0)).getArtcleProjectList(0, new MyPresenterCallBack() {
            @Override
            public void success(int code, String response) {

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
}
