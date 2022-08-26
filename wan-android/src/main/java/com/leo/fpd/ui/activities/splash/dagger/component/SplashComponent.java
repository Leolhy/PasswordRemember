package com.leo.fpd.ui.activities.splash.dagger.component;

import com.leo.base.dagger.annotation.ActivityScope;
import com.leo.fpd.app.dagger.components.AppComponent;
import com.leo.fpd.repository.RememberAccountRepository;
import com.leo.fpd.ui.activities.splash.dagger.module.SplashModule;
import com.leo.fpd.ui.activities.splash.view.SplashActivity;

import dagger.Component;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/8 11:25
 * Desc:
 */
@ActivityScope
@Component(dependencies = {AppComponent.class}, modules = {SplashModule.class})
public interface SplashComponent {

    void inject(SplashActivity activity);

    RememberAccountRepository rememberAccountRepository();
}
