package com.lixiaomi.openapp.persenter;

import com.lixiaomi.baselib.config.AppConfigInIt;
import com.lixiaomi.baselib.utils.MiJsonUtil;
import com.lixiaomi.baselib.utils.NetWorkUtils;
import com.lixiaomi.mvplib.base.BaseModel;
import com.lixiaomi.mvplib.base.BasePresenter;
import com.lixiaomi.mvplib.base.MyPresenterCallBack;
import com.lixiaomi.openapp.R;
import com.lixiaomi.openapp.bean.MyTreeBean;
import com.lixiaomi.openapp.bean.TreeBean;
import com.lixiaomi.openapp.bean.WXArticleListBean;
import com.lixiaomi.openapp.http.HttpData;
import com.lixiaomi.openapp.model.TreeModelmpl;
import com.lixiaomi.openapp.ui.fragment.SystemFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @describe：<br>
 * @author：Xiaomi<br>
 * @createTime：2019/8/1<br>
 * @remarks：<br>
 * @changeTime:<br>
 */
public class SystemFragmentPresenterImpl extends BasePresenter<SystemFragment, BaseModel> implements SystemFragmentPresenter {
    private SystemFragment mView;
    private ArrayList<MyTreeBean> mTreeList = new ArrayList<>();

    @Override
    protected ArrayList createModelList() {
        ArrayList<BaseModel> models = new ArrayList<>();
        models.add(new TreeModelmpl());
        return models;
    }

    @Override
    public void getTreeTypeList() {
        mView = getView();
        if (isViewAttached()) {
            mView.startLoading();
        }
        ((TreeModelmpl) getModelList().get(0)).getTreeTypeList(new MyPresenterCallBack() {
            @Override
            public void success(int code, String response) {
                if (isViewAttached()) {
                    mView.stopLoading();
                }
                mTreeList.clear();
                String s = "加载失败";
                try {
                    TreeBean treeBean = MiJsonUtil.getClass(response, TreeBean.class);
                    if (treeBean.getErrorCode() == 0) {
                        List<TreeBean.DataBean> data = treeBean.getData();
                        List<MyTreeBean> myDataList = new ArrayList<>();
                        if (data != null && data.size() != 0) {
                            for (int i = 0; i < data.size(); i++) {
                                TreeBean.DataBean dataBean = data.get(i);
                                List<TreeBean.DataBean.ChildrenBean> children = dataBean.getChildren();
                                List<MyTreeBean.ChildrenBean> myChildrenList = new ArrayList<>();
                                for (int i1 = 0; i1 < children.size(); i1++) {
                                    TreeBean.DataBean.ChildrenBean childrenBean = children.get(i1);
                                    MyTreeBean.ChildrenBean myChildrenBean = new MyTreeBean.ChildrenBean();
                                    myChildrenBean.setChildren(childrenBean.getChildren());
                                    myChildrenBean.setCourseId(childrenBean.getCourseId());
                                    myChildrenBean.setId(childrenBean.getId());
                                    myChildrenBean.setName(childrenBean.getName());
                                    myChildrenBean.setOrder(childrenBean.getOrder());
                                    myChildrenBean.setParentChapterId(childrenBean.getParentChapterId());
                                    myChildrenBean.setUserControlSetTop(childrenBean.isUserControlSetTop());
                                    myChildrenBean.setVisible(childrenBean.getVisible());
                                    myChildrenBean.setParentPosition(i);
                                    myChildrenBean.setPosition(i1);
                                    myChildrenList.add(myChildrenBean);
                                }
                                MyTreeBean myTreeBean = new MyTreeBean();
                                myTreeBean.setPosition(i);
                                myTreeBean.setChildren(myChildrenList);
                                myTreeBean.setVisible(dataBean.getVisible());
                                myTreeBean.setCourseId(dataBean.getCourseId());
                                myTreeBean.setId(dataBean.getId());
                                myTreeBean.setName(dataBean.getName());
                                myTreeBean.setOrder(dataBean.getOrder());
                                myTreeBean.setParentChapterId(dataBean.getParentChapterId());
                                myTreeBean.setUserControlSetTop(dataBean.isUserControlSetTop());
                                myTreeBean.setSubItems(myChildrenList);
                                myDataList.add(myTreeBean);
                            }
                            mTreeList.addAll(myDataList);
                        }
                    }
                    s = treeBean.getErrorMsg();
                    mView.setTreeData(mTreeList, HttpData.LOCAL_SUCCESS, s);
                } catch (Exception e) {
                    mView.setTreeData(mTreeList, HttpData.LOCAL_DATA_ERROR, "数据异常");
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
