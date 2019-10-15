package com.lixiaomi.openapp.persenter.activity;

import com.lixiaomi.baselib.config.AppConfigInIt;
import com.lixiaomi.baselib.utils.MiJsonUtil;
import com.lixiaomi.mvplib.base.BaseModel;
import com.lixiaomi.mvplib.base.BasePresenter;
import com.lixiaomi.mvplib.base.MiPersenterCallBack;
import com.lixiaomi.openapp.R;
import com.lixiaomi.openapp.bean.ProjectBean;
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
        ((ArticleModelImpl) getModelList().get(0)).getArtcleProjectList(page, new MiPersenterCallBack(mView) {
            @Override
            public void success(String response) {
                try {
                    mProjectList.clear();
                    ProjectBean projectBean = MiJsonUtil.getClass(response, ProjectBean.class);
                    if (projectBean.getErrorCode() == 0) {
                        List<ProjectBean.DataBean.DatasBean> data = projectBean.getData().getDatas();
                        if (data != null && data.size() != 0) {
                            mProjectList.addAll(data);
                        }
                    }
                    mView.setArticleProject(projectBean.getData().getCurPage(), projectBean.getData().getPageCount(), mProjectList);
                } catch (Exception e) {
                    mView.showToast(AppConfigInIt.getApplicationContext().getResources().getString(R.string.http_AnalysisError));
                }
            }
        });
    }
}
