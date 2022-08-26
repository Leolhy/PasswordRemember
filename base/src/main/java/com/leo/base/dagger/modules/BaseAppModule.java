package com.leo.base.dagger.modules;

import android.app.Application;

import com.google.gson.Gson;
import com.jeremyliao.liveeventbus.LiveEventBus;
import com.jeremyliao.liveeventbus.core.Observable;
import com.leo.base.entities.event.BaseAppEvent;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/7/29 16:37
 * Desc:
 */
@Module
public class BaseAppModule {
    private final Application application;

    public BaseAppModule(Application application) {
        this.application = application;
    }

    @Singleton
    @Provides
    public Application provideApplication() {
        return this.application;
    }

    @Singleton
    @Provides
    public Gson provideGson() {
        return new Gson();
    }

    @Singleton
    @Provides
    public Observable<BaseAppEvent> provideEventBus() {
        return LiveEventBus.get("BaseAppLiveEventBus", BaseAppEvent.class);
    }
}
