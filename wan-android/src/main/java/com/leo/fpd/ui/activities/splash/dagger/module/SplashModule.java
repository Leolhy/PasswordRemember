package com.leo.fpd.ui.activities.splash.dagger.module;

import android.view.LayoutInflater;

import com.leo.base.dagger.annotation.ActivityScope;
import com.leo.base.dagger.modules.BaseActivityModule;
import com.leo.fpd.databinding.ActivitySplashBinding;

import dagger.Module;
import dagger.Provides;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/8 11:26
 * Desc:
 */
@Module
public class SplashModule extends BaseActivityModule {

    public SplashModule(LayoutInflater inflater) {
        super(inflater);
    }

    @ActivityScope
    @Provides
    public ActivitySplashBinding provideActivitySplashBinding() {
        return ActivitySplashBinding.inflate(inflater);
    }
}
