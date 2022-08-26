package com.leo.fpd.source;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagingState;
import androidx.paging.rxjava3.RxPagingSource;

import com.leo.base.http.PageResponse;
import com.leo.fpd.api.ArticleApi;
import com.leo.fpd.apiImpl.ArticleApiImpl;
import com.leo.fpd.entities.ArticleEntity;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/11 10:01
 * Desc:
 */
public class ArticlePagingSource extends RxPagingSource<Integer, ArticleEntity> {

    private final ArticleApiImpl articleApi;

    @Inject
    public ArticlePagingSource(ArticleApiImpl articleApi) {
        this.articleApi = articleApi;
    }

    public ArticleApiImpl getArticleApi() {
        return articleApi;
    }

    @Nullable
    @Override
    public Integer getRefreshKey(@NonNull PagingState<Integer, ArticleEntity> state) {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        Integer anchorPosition = state.getAnchorPosition();
        if (anchorPosition == null) {
            return null;
        }
        LoadResult.Page<Integer, ArticleEntity> anchorPage = state.closestPageToPosition(anchorPosition);
        if (anchorPage == null) {
            return null;
        }
        Integer prevKey = anchorPage.getPrevKey();
        if (prevKey != null) {
            return prevKey + 1;
        }
        Integer nextKey = anchorPage.getNextKey();
        if (nextKey != null) {
            return nextKey - 1;
        }
        return null;
    }

    @NonNull
    @Override
    public Single<LoadResult<Integer, ArticleEntity>> loadSingle(@NonNull LoadParams<Integer> params) {
        Integer pageNum = params.getKey();
        pageNum = pageNum == null ? 0 : pageNum;
        return articleApi.createServer(ArticleApi.class)
                .getPageArticle(pageNum)
                .subscribeOn(Schedulers.io())
                .map(this::toLoadResult)
                .onErrorReturn(LoadResult.Error::new);
    }

    private LoadResult<Integer, ArticleEntity> toLoadResult(@NonNull PageResponse<ArticleEntity> response) {
        return new LoadResult.Page<>(
                response.getData().getDatas(),
                null, // Only paging forward.
                response.getData().getCurPage());
    }
}
