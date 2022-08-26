package com.leo.fpd.ui.activities.main.dagger.module;

import android.view.LayoutInflater;

import com.google.gson.annotations.SerializedName;
import com.leo.base.dagger.annotation.ActivityScope;
import com.leo.base.dagger.modules.BaseActivityModule;
import com.leo.fpd.databinding.ActivityMainBinding;

import dagger.Module;
import dagger.Provides;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/5 15:39
 * Desc:
 */
@Module
public class MainModule extends BaseActivityModule {

    public MainModule(LayoutInflater inflater) {
        super(inflater);
    }

    @ActivityScope
    @Provides
    public ActivityMainBinding provideActivityMainBinding() {
        return ActivityMainBinding.inflate(inflater);
    }


}
