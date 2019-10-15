package com.lixiaomi.openapp.persenter.activity;

import com.lixiaomi.baselib.config.AppConfigInIt;
import com.lixiaomi.baselib.utils.MiJsonUtil;
import com.lixiaomi.mvplib.base.BaseModel;
import com.lixiaomi.mvplib.base.BasePresenter;
import com.lixiaomi.mvplib.base.MiPersenterCallBack;
import com.lixiaomi.openapp.R;
import com.lixiaomi.openapp.bean.TreeArticleListBean;
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
        ((TreeModelmpl) getModelList().get(0)).getTreeList(page, cId, new MiPersenterCallBack(mView) {
            @Override
            public void success(String response) {
                try {
                    mTreeArticleList.clear();
                    TreeArticleListBean treeBean = MiJsonUtil.getClass(response, TreeArticleListBean.class);
                    if (treeBean.getErrorCode() == 0) {
                        List<TreeArticleListBean.DataBean.DatasBean> data = treeBean.getData().getDatas();
                        if (data != null && data.size() != 0) {
                            mTreeArticleList.addAll(data);
                        }
                    }
                    mView.setSystemArticleList(treeBean.getData().getCurPage(), treeBean.getData().getPageCount(), mTreeArticleList);
                } catch (Exception e) {
                    mView.showToast(AppConfigInIt.getApplicationContext().getResources().getString(R.string.http_AnalysisError));
                }
            }
        });
    }
}
