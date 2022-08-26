package com.leo.fpd.ui.fragments.article.dagger.component;

import com.leo.base.dagger.annotation.FragmentScope;
import com.leo.fpd.app.dagger.components.AppComponent;
import com.leo.fpd.ui.fragments.article.dagger.module.ArticleModule;
import com.leo.fpd.ui.fragments.article.view.ArticleFragment;
import com.leo.fpd.ui.fragments.article.view.TopArticleFragment;

import dagger.Component;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/10 15:52
 * Desc:
 */
@FragmentScope
@Component(dependencies = {AppComponent.class}, modules = {ArticleModule.class})
public interface ArticleComponent {

    void inject(ArticleFragment fragment);

    void inject(TopArticleFragment fragment);
}
