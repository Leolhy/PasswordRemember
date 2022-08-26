package com.leo.fpd.repository;

import com.leo.fpd.source.ArticlePagingSource;

import javax.inject.Inject;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/11 9:56
 * Desc:
 */
public class ArticleRepository {

    private final ArticlePagingSource articlePagingSource;

    @Inject
    public ArticleRepository(ArticlePagingSource articlePagingSource) {
        this.articlePagingSource = articlePagingSource;
    }

    public ArticlePagingSource getArticlePagingSource() {
        return articlePagingSource;
    }
}
