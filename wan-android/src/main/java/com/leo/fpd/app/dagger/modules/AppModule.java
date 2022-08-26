package com.leo.fpd.app.dagger.modules;

import android.app.Application;

import com.leo.base.dagger.annotation.AppScope;
import com.leo.fpd.app.FpdApplication;

import dagger.Module;
import dagger.Provides;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/5 15:18
 * Desc:
 */
@Module
public class AppModule {

    @AppScope
    @Provides
    public FpdApplication providerFpdApplication(Application application) {
        return (FpdApplication) application;
    }




}
