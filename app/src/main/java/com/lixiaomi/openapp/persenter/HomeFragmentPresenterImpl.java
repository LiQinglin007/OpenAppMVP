package com.lixiaomi.openapp.persenter;

import com.lixiaomi.baselib.config.AppConfigInIt;
import com.lixiaomi.baselib.utils.MiJsonUtil;
import com.lixiaomi.mvplib.base.BaseModel;
import com.lixiaomi.mvplib.base.BasePresenter;
import com.lixiaomi.mvplib.base.MiPersenterCallBack;
import com.lixiaomi.openapp.R;
import com.lixiaomi.openapp.bean.ArticleBean;
import com.lixiaomi.openapp.bean.BannerBean;
import com.lixiaomi.openapp.bean.ProjectBean;
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
public class HomeFragmentPresenterImpl extends BasePresenter<HomeFragment, BaseModel> implements HomeFragmentPresenter {
    private HomeFragment mView;

    private ArrayList<BannerBean.DataBean> mBannerList = new ArrayList<>();
    private ArrayList<ProjectBean.DataBean.DatasBean> mProjectList = new ArrayList<>();
    private ArrayList<ArticleBean.DataBean.DatasBean> mArticleList = new ArrayList<>();

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
        ((PublicModelImpl) getModelList().get(0)).getBannerList(new MiPersenterCallBack(mView) {
            @Override
            public void success(String response) {
                try {
                    mBannerList.clear();
                    BannerBean roomListBean = MiJsonUtil.getClass(response, BannerBean.class);
                    if (roomListBean.getErrorCode() == 0) {
                        List<BannerBean.DataBean> data = roomListBean.getData();
                        if (data != null && data.size() != 0) {
                            mBannerList.addAll(data);
                        }
                    }
                    mView.setBannerData(mBannerList);
                } catch (Exception e) {
                    mView.showToast(AppConfigInIt.getApplicationContext().getResources().getString(R.string.http_AnalysisError));
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
        ((ArticleModelImpl) getModelList().get(1)).getArtcleList(0, new MiPersenterCallBack(mView) {
            @Override
            public void success(String response) {
                try {
                    mArticleList.clear();
                    ArticleBean articleBean = MiJsonUtil.getClass(response, ArticleBean.class);
                    if (articleBean.getErrorCode() == 0) {
                        List<ArticleBean.DataBean.DatasBean> data = articleBean.getData().getDatas();
                        if (data != null && data.size() != 0) {
                            if (data.size() > 5) {
                                mArticleList.add(data.get(0));
                                mArticleList.add(data.get(1));
                                mArticleList.add(data.get(2));
                                mArticleList.add(data.get(3));
                                mArticleList.add(data.get(4));
                            } else {
                                mArticleList.addAll(data);
                            }
                        }
                    }
                    mView.setArticle(mArticleList);
                } catch (Exception e) {
                    mView.showToast(AppConfigInIt.getApplicationContext().getResources().getString(R.string.http_AnalysisError));
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
        ((ArticleModelImpl) getModelList().get(1)).getArtcleProjectList(0, new MiPersenterCallBack(mView) {
            @Override
            public void success(String response) {
                try {
                    mProjectList.clear();
                    ProjectBean projectBean = MiJsonUtil.getClass(response, ProjectBean.class);
                    if (projectBean.getErrorCode() == 0) {
                        List<ProjectBean.DataBean.DatasBean> data = projectBean.getData().getDatas();
                        if (data != null && data.size() != 0) {
                            if (data.size() > 5) {
                                mProjectList.add(data.get(0));
                                mProjectList.add(data.get(1));
                                mProjectList.add(data.get(2));
                                mProjectList.add(data.get(3));
                                mProjectList.add(data.get(4));
                            } else {
                                mProjectList.addAll(data);
                            }
                        }
                    }
                    mView.setArticleProject(mProjectList);
                } catch (Exception e) {
                    mView.showToast(AppConfigInIt.getApplicationContext().getResources().getString(R.string.http_AnalysisError));
                }
            }
        });
    }
}
