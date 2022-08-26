package com.leo.fpd.ui.fragments.home.dagger.module;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leo.base.dagger.annotation.FragmentScope;
import com.leo.base.dagger.modules.BaseFragmentModule;
import com.leo.fpd.databinding.FragmentHomeBinding;

import dagger.Module;
import dagger.Provides;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/10 11:49
 * Desc:
 */
@Module
public class HomeModule extends BaseFragmentModule {

    public HomeModule(LayoutInflater inflater, ViewGroup container) {
        super(inflater, container);
    }

    @FragmentScope
    @Provides
    public FragmentHomeBinding provideFragmentHomeBinding() {
        return FragmentHomeBinding.inflate(inflater, container, false);
    }


}
