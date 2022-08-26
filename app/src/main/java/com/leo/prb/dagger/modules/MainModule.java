package com.leo.prb.dagger.modules;

import android.view.LayoutInflater;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;

import com.leo.prb.R;
import com.leo.prb.dagger.annotation.ActivityScope;
import com.leo.prb.databinding.ActivityMainBinding;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/2/16 11:29
 * Desc:
 */
@Module
public class MainModule {

    private final LayoutInflater inflater;

    public MainModule(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @ActivityScope
    @Provides
    public ActivityMainBinding provideActivityMainBinding() {
        return ActivityMainBinding.inflate(inflater);
    }

    @ActivityScope
    @Provides
    @LayoutRes
    @Named("accountItem")
    public int provideAccountItemLayout() {
        return R.layout.item_account;
    }
}
