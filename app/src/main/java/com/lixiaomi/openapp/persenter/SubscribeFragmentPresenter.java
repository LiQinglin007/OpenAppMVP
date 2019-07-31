package com.lixiaomi.openapp.persenter;

/**
 * @describe：<br>
 * @author：Xiaomi<br>
 * @createTime：2019/7/31<br>
 * @remarks：<br>
 * @changeTime:<br>
 */
public interface SubscribeFragmentPresenter {
    /**
     * 获取公众号列表
     */
    void getWXAuthorList();

    /**
     * 获取公众号下的文章列表
     *
     * @param authorId 公众号id
     * @param page     页码
     */
    void getWXArticleList(String authorId, int page);
}
