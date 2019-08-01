package com.lixiaomi.openapp.persenter.activity;

import com.lixiaomi.baselib.config.AppConfigInIt;
import com.lixiaomi.baselib.utils.MiJsonUtil;
import com.lixiaomi.baselib.utils.NetWorkUtils;
import com.lixiaomi.mvplib.base.BaseModel;
import com.lixiaomi.mvplib.base.BasePresenter;
import com.lixiaomi.mvplib.base.MyPresenterCallBack;
import com.lixiaomi.openapp.R;
import com.lixiaomi.openapp.bean.TreeArticleListBean;
import com.lixiaomi.openapp.http.HttpData;
import com.lixiaomi.openapp.model.TreeModelmpl;
import com.lixiaomi.openapp.ui.activity.SystemListActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @describe：<br>
 * @author：Xiaomi<br>
 * @createTime：2019/8/1<br>
 * @remarks：<br>
 * @changeTime:<br>
 */
public class SystemListActivityPresenterImpl extends BasePresenter<SystemListActivity, BaseModel> implements SystemListActivityPresenter {

    private SystemListActivity mView;
    private ArrayList<TreeArticleListBean.DataBean.DatasBean> mTreeArticleList = new ArrayList<>();

    @Override
    protected ArrayList createModelList() {
        ArrayList<BaseModel> models = new ArrayList<>();
        models.add(new TreeModelmpl());
        return models;
    }

    @Override
    public void getSystemArticle(boolean showLoading, int page, String cId) {
        mView = getView();
        if (isViewAttached()) {
            if (showLoading) {
                mView.startLoading();
            }
        }
        ((TreeModelmpl) getModelList().get(0)).getTreeList(page, cId, new MyPresenterCallBack() {
            @Override
            public void success(int code, String response) {
                if (isViewAttached()) {
                    mView.stopLoading();
                }
                mTreeArticleList.clear();
                String s = "加载失败";
                try {
                    TreeArticleListBean treeBean = MiJsonUtil.getClass(response, TreeArticleListBean.class);
                    if (treeBean.getErrorCode() == 0) {
                        List<TreeArticleListBean.DataBean.DatasBean> data = treeBean.getData().getDatas();
                        if (data != null && data.size() != 0) {
                            mTreeArticleList.addAll(data);
                        }
                    }
                    s = treeBean.getErrorMsg();
                    mView.setSystemArticleList(treeBean.getData().getPageCount(), mTreeArticleList, HttpData.LOCAL_SUCCESS, s);
                } catch (Exception e) {
                    mView.setSystemArticleList(0, mTreeArticleList, HttpData.LOCAL_DATA_ERROR, "数据异常");
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
}
