package com.leo.fpd.apiImpl;

import com.leo.base.http.ListResponse;
import com.leo.fpd.api.ArticleApi;
import com.leo.fpd.entities.ArticleEntity;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/11 10:03
 * Desc:
 */
public class ArticleApiImpl extends BaseWanAndroidApi {

    @Inject
    public ArticleApiImpl() {
        super();
    }


    public Observable<ListResponse<ArticleEntity>> loadTopArticle() {
        return createServer(ArticleApi.class)
                .getTopArticle()
                .compose(ioMain());
    }

}
