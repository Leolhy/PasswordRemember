package com.leo.fpd.ui.fragments.project.dagger.module;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leo.base.dagger.annotation.FragmentScope;
import com.leo.base.dagger.modules.BaseFragmentModule;
import com.leo.fpd.databinding.FragmentProjectBinding;
import com.leo.fpd.databinding.FragmentSystemBinding;

import dagger.Module;
import dagger.Provides;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/10 14:30
 * Desc:
 */
@Module
public class ProjectModule extends BaseFragmentModule {

    public ProjectModule(LayoutInflater inflater, ViewGroup container) {
        super(inflater, container);
    }

    @FragmentScope
    @Provides
    public FragmentProjectBinding provideFragmentProjectBinding() {
        return FragmentProjectBinding.inflate(inflater, container, false);
    }


}
