package com.leo.prb.dagger.modules;

import android.view.LayoutInflater;

import com.leo.prb.dagger.annotation.ActivityScope;
import com.leo.prb.databinding.ActivityAccountDetailBinding;
import com.leo.prb.databinding.ActivityAccountTypeAddBinding;

import dagger.Module;
import dagger.Provides;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/3/4 16:24
 * Desc:
 */
@Module
public class TypeModule {

    private final LayoutInflater inflater;

    public TypeModule(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @ActivityScope
    @Provides
    public ActivityAccountTypeAddBinding provideBinding() {
        return ActivityAccountTypeAddBinding.inflate(inflater);
    }
}
