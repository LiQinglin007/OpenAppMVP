package com.lixiaomi.openapp.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lixiaomi.openapp.R;
import com.lixiaomi.openapp.bean.TreeArticleListBean;
import com.lixiaomi.openapp.bean.WXArticleListBean;

import java.util.List;

/**
 * @describe：体系文章列表<br>
 * @author：Xiaomi<br>
 * @createTime：2019/7/31<br>
 * @remarks：<br>
 * @changeTime:<br>
 */
public class SystemActivityAdapter extends BaseQuickAdapter<TreeArticleListBean.DataBean.DatasBean, BaseViewHolder> {

    public SystemActivityAdapter(int layoutResId, @Nullable List<TreeArticleListBean.DataBean.DatasBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TreeArticleListBean.DataBean.DatasBean item) {
        helper.setText(R.id.item_sys_article_title, item.getTitle())
                .setText(R.id.item_sys_article_author, "时间：" + item.getNiceDate());
    }
}
