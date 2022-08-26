package com.leo.fpd.ui.fragments.mine.dagger.module;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leo.base.dagger.annotation.FragmentScope;
import com.leo.base.dagger.modules.BaseFragmentModule;
import com.leo.fpd.databinding.FragmentMineBinding;

import dagger.Module;
import dagger.Provides;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/10 14:06
 * Desc:
 */
@Module
public class MineModule extends BaseFragmentModule {

    public MineModule(LayoutInflater inflater, ViewGroup container) {
        super(inflater, container);
    }

    @FragmentScope
    @Provides
    public FragmentMineBinding provideFragmentMineBinding() {
        return FragmentMineBinding.inflate(inflater, container, false);
    }

}
