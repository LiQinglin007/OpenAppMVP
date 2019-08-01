package com.lixiaomi.openapp.ui.fragment;

import com.lixiaomi.mvplib.base.BaseView;
import com.lixiaomi.openapp.bean.MyTreeBean;

import java.util.ArrayList;

/**
 * @describe：<br>
 * @author：Xiaomi<br>
 * @createTime：2019/7/30<br>
 * @remarks：<br>
 * @changeTime:<br>
 */
public interface SystemFragment extends BaseView {

    void setTreeData(ArrayList<MyTreeBean> treeList, int code, String msg);

}
