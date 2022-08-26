package com.leo.prb.dagger.modules;

import android.view.LayoutInflater;

import com.leo.prb.dagger.annotation.ActivityScope;
import com.leo.prb.databinding.ActivityAccountDetailBinding;
import com.leo.prb.databinding.ItemAccountTypeBinding;

import dagger.Module;
import dagger.Provides;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/3/4 11:22
 * Desc:
 */
@Module
public class DetailModule {
    private final LayoutInflater inflater;

    public DetailModule(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @ActivityScope
    @Provides
    public ActivityAccountDetailBinding provideBinding() {
        return ActivityAccountDetailBinding.inflate(inflater);
    }


    @ActivityScope
    @Provides
    public ItemAccountTypeBinding provideItemBinding() {
        return ItemAccountTypeBinding.inflate(inflater);
    }

    @ActivityScope
    @Provides
    public LayoutInflater provideLayoutInflater() {
        return inflater;
    }
}
