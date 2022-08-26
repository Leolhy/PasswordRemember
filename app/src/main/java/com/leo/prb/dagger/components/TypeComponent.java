package com.leo.prb.dagger.components;

import com.leo.prb.dagger.annotation.ActivityScope;
import com.leo.prb.dagger.modules.TypeModule;
import com.leo.prb.mvvm.type.view.AccountTypeAddActivity;

import dagger.Component;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/3/4 16:24
 * Desc:
 */
@ActivityScope
@Component(dependencies = {AppComponent.class}, modules = {TypeModule.class})
public interface TypeComponent {

    void inject(AccountTypeAddActivity activity);
}
