package com.leo.base.dagger.components;

import android.app.Application;

import com.google.gson.Gson;
import com.jeremyliao.liveeventbus.core.Observable;
import com.leo.base.dagger.modules.BaseAppModule;
import com.leo.base.entities.event.BaseAppEvent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/7/29 16:36
 * Desc:
 */
@Singleton
@Component(modules = {BaseAppModule.class})
public interface BaseAppComponent {

    Application application();

    Gson gson();

    Observable<BaseAppEvent> appEventBus();
}
