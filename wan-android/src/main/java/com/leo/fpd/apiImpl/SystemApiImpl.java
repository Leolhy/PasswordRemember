package com.leo.fpd.apiImpl;

import com.leo.base.http.ListResponse;
import com.leo.base.http.PageResponse;
import com.leo.fpd.api.ArticleApi;
import com.leo.fpd.api.SystemApi;
import com.leo.fpd.entities.ArticleEntity;
import com.leo.fpd.entities.SystemClassEntity;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/17 11:41
 * Desc:
 */
public class SystemApiImpl extends BaseWanAndroidApi {

    @Inject
    public SystemApiImpl() {
    }

    public Observable<ListResponse<SystemClassEntity>> loadSystemClassTree() {
        return createServer(SystemApi.class)
                .getSystemClassTree()
                .compose(ioMain());
    }

    public Observable<PageResponse<ArticleEntity>> loadPageSystemArticle(int page, int cid) {
        return createServer(ArticleApi.class)
                .getPageSystemArticle(page, cid)
                .compose(ioMain());
    }


}
