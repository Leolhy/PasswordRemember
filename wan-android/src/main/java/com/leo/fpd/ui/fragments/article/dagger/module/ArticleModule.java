package com.leo.fpd.ui.fragments.article.dagger.module;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leo.base.dagger.annotation.FragmentScope;
import com.leo.base.dagger.modules.BaseFragmentModule;
import com.leo.fpd.databinding.FragmentArticleBinding;
import com.leo.fpd.databinding.FragmentTopArticleBinding;

import dagger.Module;
import dagger.Provides;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/10 15:53
 * Desc:
 */
@Module
public class ArticleModule extends BaseFragmentModule {

    public ArticleModule(LayoutInflater inflater, ViewGroup container) {
        super(inflater, container);
    }

    @FragmentScope
    @Provides
    public FragmentArticleBinding provideFragmentArticleBinding() {
        return FragmentArticleBinding.inflate(inflater, container, false);
    }

    @FragmentScope
    @Provides
    public FragmentTopArticleBinding provideFragmentTopArticleBinding() {
        return FragmentTopArticleBinding.inflate(inflater, container, false);
    }

}
