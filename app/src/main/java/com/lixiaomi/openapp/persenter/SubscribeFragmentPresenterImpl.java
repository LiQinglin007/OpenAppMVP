package com.lixiaomi.openapp.persenter;

import com.lixiaomi.baselib.config.AppConfigInIt;
import com.lixiaomi.baselib.utils.MiJsonUtil;
import com.lixiaomi.mvplib.base.BaseModel;
import com.lixiaomi.mvplib.base.BasePresenter;
import com.lixiaomi.mvplib.base.MiPersenterCallBack;
import com.lixiaomi.openapp.R;
import com.lixiaomi.openapp.bean.WXArticleAuthorlistBean;
import com.lixiaomi.openapp.bean.WXArticleListBean;
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
        ((WXArticleModelImpl) getModelList().get(0)).getWXArticleAuthorList(new MiPersenterCallBack(mView) {
            @Override
            public void success(String response) {
                try {
                    mAuthorList.clear();
                    WXArticleAuthorlistBean articleAuthorlistBean = MiJsonUtil.getClass(response, WXArticleAuthorlistBean.class);
                    if (articleAuthorlistBean.getErrorCode() == 0) {
                        List<WXArticleAuthorlistBean.DataBean> data = articleAuthorlistBean.getData();
                        if (data != null && data.size() != 0) {
                            mAuthorList.addAll(data);
                        }
                    }
                    mView.setAuthorListData(mAuthorList);
                } catch (Exception e) {
                    mView.showToast(AppConfigInIt.getApplicationContext().getResources().getString(R.string.http_AnalysisError));
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
        ((WXArticleModelImpl) getModelList().get(0)).getWXArticleListByAuthorId(authorId, page, new MiPersenterCallBack(mView) {
            @Override
            public void success(String response) {
                try {
                    mArticleList.clear();
                    WXArticleListBean articleListBean = MiJsonUtil.getClass(response, WXArticleListBean.class);
                    if (articleListBean.getErrorCode() == 0) {
                        List<WXArticleListBean.DataBean.DatasBean> data = articleListBean.getData().getDatas();
                        if (data != null && data.size() != 0) {
                            mArticleList.addAll(data);
                        }
                    }
                    mView.setArticleListData(articleListBean.getData().getCurPage(), articleListBean.getData().getPageCount(), mArticleList);
                } catch (Exception e) {
                    mView.showToast(AppConfigInIt.getApplicationContext().getResources().getString(R.string.http_AnalysisError));
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
