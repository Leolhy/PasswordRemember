package com.leo.prb.dagger.components;

import com.leo.prb.dagger.annotation.ActivityScope;
import com.leo.prb.dagger.modules.SettingModule;
import com.leo.prb.mvvm.setting.view.SettingActivity;

import dagger.Component;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/4/21 14:10
 * Desc:
 */
@ActivityScope
@Component(dependencies = {AppComponent.class}, modules = {SettingModule.class})
public interface SettingComponent {

    void inject(SettingActivity activity);

}
