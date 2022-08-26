package com.leo.fpd.ui.fragments.qaa.dagger.module;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leo.base.dagger.annotation.FragmentScope;
import com.leo.base.dagger.modules.BaseFragmentModule;
import com.leo.fpd.databinding.FragmentQaaBinding;

import dagger.Module;
import dagger.Provides;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/10 16:04
 * Desc:
 */
@Module
public class QaaModule extends BaseFragmentModule {

    public QaaModule(LayoutInflater inflater, ViewGroup container) {
        super(inflater, container);
    }

    @FragmentScope
    @Provides
    public FragmentQaaBinding provideFragmentQaaBinding() {
        return FragmentQaaBinding.inflate(inflater, container, false);
    }

}
