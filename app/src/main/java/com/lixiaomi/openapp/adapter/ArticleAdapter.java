package com.lixiaomi.openapp.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lixiaomi.openapp.R;
import com.lixiaomi.openapp.bean.ArticleBean;

/**
 * @describe：<br>
 * @author：Xiaomi<br>
 * @createTime：2019/7/31<br>
 * @remarks：<br>
 * @changeTime:<br>
 */
public class ArticleAdapter extends BaseQuickAdapter<ArticleBean.DataBean.DatasBean, BaseViewHolder> {

    public ArticleAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleBean.DataBean.DatasBean item) {
        helper.setText(R.id.item_article_title, item.getTitle())
                .setText(R.id.item_article_author, item.getAuthor() + "  " + item.getNiceDate())
                .setText(R.id.item_article_type, item.getSuperChapterName());
    }
}
