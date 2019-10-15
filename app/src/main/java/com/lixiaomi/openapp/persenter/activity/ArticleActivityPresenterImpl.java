package com.lixiaomi.openapp.persenter.activity;

import com.lixiaomi.baselib.config.AppConfigInIt;
import com.lixiaomi.baselib.utils.MiJsonUtil;
import com.lixiaomi.mvplib.base.BaseModel;
import com.lixiaomi.mvplib.base.BasePresenter;
import com.lixiaomi.mvplib.base.MiPersenterCallBack;
import com.lixiaomi.openapp.R;
import com.lixiaomi.openapp.bean.ArticleBean;
import com.lixiaomi.openapp.model.ArticleModelImpl;
import com.lixiaomi.openapp.ui.activity.ArticleActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @describe：<br>
 * @author：Xiaomi<br>
 * @createTime：2019/8/1<br>
 * @remarks：<br>
 * @changeTime:<br>
 */
public class ArticleActivityPresenterImpl extends BasePresenter<ArticleActivity, BaseModel> implements ArticleActivityPresenter {
    private ArticleActivity mView;
    private ArrayList<ArticleBean.DataBean.DatasBean> mArticleList = new ArrayList<>();

    @Override
    protected ArrayList createModelList() {
        ArrayList<BaseModel> models = new ArrayList<>();
        models.add(new ArticleModelImpl());
        return models;
    }

    @Override
    public void getArticleData(boolean showLoading, int page) {
        mView = getView();
        if (isViewAttached()) {
            if (showLoading) {
                mView.startLoading();
            }
        }
        ((ArticleModelImpl) getModelList().get(0)).getArtcleList(page, new MiPersenterCallBack(mView) {
            @Override
            public void success(String response) {
                try {
                    mArticleList.clear();
                    ArticleBean articleBean = MiJsonUtil.getClass(response, ArticleBean.class);
                    if (articleBean.getErrorCode() == 0) {
                        List<ArticleBean.DataBean.DatasBean> data = articleBean.getData().getDatas();
                        if (data != null && data.size() != 0) {
                            mArticleList.addAll(data);
                        }
                    }
                    mView.setArticle(articleBean.getData().getCurPage(), articleBean.getData().getPageCount(), mArticleList);
                } catch (Exception e) {
                    mView.showToast(AppConfigInIt.getApplicationContext().getResources().getString(R.string.http_AnalysisError));
                }
            }
        });
    }
}
