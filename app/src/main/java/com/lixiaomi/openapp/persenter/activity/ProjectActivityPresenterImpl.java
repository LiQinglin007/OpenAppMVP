package com.lixiaomi.openapp.persenter.activity;

import com.lixiaomi.baselib.config.AppConfigInIt;
import com.lixiaomi.baselib.utils.MiJsonUtil;
import com.lixiaomi.baselib.utils.NetWorkUtils;
import com.lixiaomi.mvplib.base.BaseModel;
import com.lixiaomi.mvplib.base.BasePresenter;
import com.lixiaomi.mvplib.base.MyPresenterCallBack;
import com.lixiaomi.openapp.R;
import com.lixiaomi.openapp.bean.ProjectBean;
import com.lixiaomi.openapp.http.HttpData;
import com.lixiaomi.openapp.model.ArticleModelImpl;
import com.lixiaomi.openapp.ui.activity.ProjectActivity;
import java.util.ArrayList;
import java.util.List;

/**
 * @describe：<br>
 * @author：Xiaomi<br>
 * @createTime：2019/8/1<br>
 * @remarks：<br>
 * @changeTime:<br>
 */
public class ProjectActivityPresenterImpl extends BasePresenter<ProjectActivity, BaseModel> implements ProjectActivityPresenter {
    private ProjectActivity mView;
    private ArrayList<ProjectBean.DataBean.DatasBean> mProjectList = new ArrayList<>();

    @Override
    protected ArrayList createModelList() {
        ArrayList<BaseModel> models = new ArrayList<>();
        models.add(new ArticleModelImpl());
        return models;
    }

    @Override
    public void getProjectData(boolean showLoading, int page) {
        mView = getView();
        if (isViewAttached()) {
            if (showLoading) {
                mView.startLoading();
            }
        }
        ((ArticleModelImpl) getModelList().get(0)).getArtcleList(page, new MyPresenterCallBack() {
            @Override
            public void success(int code, String response) {
                if (isViewAttached()) {
                    mView.stopLoading();
                }
                mProjectList.clear();
                String s = "加载失败";
                try {
                    ProjectBean projectBean = MiJsonUtil.getClass(response, ProjectBean.class);
                    if (projectBean.getErrorCode() == 0) {
                        List<ProjectBean.DataBean.DatasBean> data = projectBean.getData().getDatas();
                        if (data != null && data.size() != 0) {
                            mProjectList.addAll(data);
                        }
                    }
                    s = projectBean.getErrorMsg();
                    mView.setArticleProject(projectBean.getData().getPageCount(), mProjectList, HttpData.LOCAL_SUCCESS, s);
                } catch (Exception e) {
                    mView.setArticleProject(0, mProjectList, HttpData.LOCAL_DATA_ERROR, "数据异常");
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
