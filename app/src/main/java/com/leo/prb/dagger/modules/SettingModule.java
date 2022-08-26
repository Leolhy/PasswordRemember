package com.leo.prb.dagger.modules;

import android.view.LayoutInflater;

import com.leo.prb.dagger.annotation.ActivityScope;
import com.leo.prb.databinding.ActivitySettingBinding;

import dagger.Module;
import dagger.Provides;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/4/21 14:11
 * Desc:
 */
@Module
public class SettingModule {

    private LayoutInflater inflater;


    public SettingModule(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @ActivityScope
    @Provides
    public ActivitySettingBinding provideActivitySettingBinding() {
        return ActivitySettingBinding.inflate(inflater);
    }
}
