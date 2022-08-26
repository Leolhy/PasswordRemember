package com.leo.fpd.app.dagger.components;

import com.google.gson.Gson;
import com.jeremyliao.liveeventbus.core.Observable;
import com.leo.base.dagger.annotation.AppScope;
import com.leo.base.dagger.components.BaseAppComponent;
import com.leo.base.entities.event.BaseAppEvent;
import com.leo.fpd.app.FpdApplication;
import com.leo.fpd.app.dagger.modules.AppModule;
import com.leo.fpd.app.dagger.modules.BoxModule;
import com.leo.fpd.entities.RememberAccountEntity;
import com.leo.fpd.entities.UserEntity;
import com.leo.fpd.repository.RememberAccountRepository;

import dagger.Component;
import io.objectbox.Box;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/5 15:17
 * Desc:
 */
@AppScope
@Component(dependencies = {BaseAppComponent.class}, modules = {AppModule.class, BoxModule.class})
public interface AppComponent {

    FpdApplication fpdApplication();

    Observable<BaseAppEvent> appEventBus();

    Box<UserEntity> userBox();

    RememberAccountRepository rememberAccountRepository();
}
