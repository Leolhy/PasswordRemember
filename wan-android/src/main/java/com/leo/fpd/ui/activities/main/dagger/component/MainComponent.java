package com.leo.fpd.ui.activities.main.dagger.component;

import com.leo.base.dagger.annotation.ActivityScope;
import com.leo.fpd.app.dagger.components.AppComponent;
import com.leo.fpd.ui.activities.main.dagger.module.MainModule;
import com.leo.fpd.ui.activities.main.view.MainActivity;

import dagger.Component;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/5 15:42
 * Desc:
 */
@ActivityScope
@Component(dependencies = {AppComponent.class}, modules = {MainModule.class})
public interface MainComponent {

    void inject(MainActivity activity);

}
