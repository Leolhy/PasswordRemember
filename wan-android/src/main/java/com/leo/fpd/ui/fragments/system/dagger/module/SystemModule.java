package com.leo.fpd.ui.fragments.system.dagger.module;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leo.base.dagger.annotation.FragmentScope;
import com.leo.base.dagger.modules.BaseFragmentModule;
import com.leo.fpd.databinding.FragmentSystemBinding;

import dagger.Module;
import dagger.Provides;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/10 14:22
 * Desc:
 */
@Module
public class SystemModule extends BaseFragmentModule {

    public SystemModule(LayoutInflater inflater, ViewGroup container) {
        super(inflater, container);
    }

    @FragmentScope
    @Provides
    public FragmentSystemBinding provideFragmentSystemBinding() {
        return FragmentSystemBinding.inflate(inflater, container, false);
    }

}
