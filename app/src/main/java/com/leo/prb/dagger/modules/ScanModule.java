package com.leo.prb.dagger.modules;

import android.view.LayoutInflater;

import com.leo.prb.dagger.annotation.ActivityScope;
import com.leo.prb.databinding.ActivityMainBinding;
import com.leo.prb.databinding.ActivityScanBinding;

import dagger.Module;
import dagger.Provides;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/5/19 14:52
 * Desc:
 */
@Module
public class ScanModule {

    private final LayoutInflater inflater;

    public ScanModule(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @ActivityScope
    @Provides
    public ActivityScanBinding provideActivityScanBinding() {
        return ActivityScanBinding.inflate(inflater);
    }


}
