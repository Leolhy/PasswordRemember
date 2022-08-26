package com.leo.fpd.api;

import com.leo.base.http.ListResponse;
import com.leo.base.http.PageResponse;
import com.leo.fpd.entities.ArticleEntity;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/11 9:48
 * Desc:
 */
public interface ArticleApi {

    @GET("/article/list/{page}/json")
    Single<PageResponse<ArticleEntity>> getPageArticle(@Path("page") int page);

    @GET("/article/top/json")
    Observable<ListResponse<ArticleEntity>> getTopArticle();

    @GET("/article/list/{page}/json")
    Observable<PageResponse<ArticleEntity>> getPageSystemArticle(@Path("page") int page, @Query("cid") int cid);
    
}
