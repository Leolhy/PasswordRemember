package com.leo.prb.dagger.components;

import com.leo.prb.dagger.annotation.ActivityScope;
import com.leo.prb.dagger.modules.DetailModule;
import com.leo.prb.mvvm.detail.view.AccountDetailActivity;

import dagger.Component;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/3/4 11:23
 * Desc:
 */
@ActivityScope
@Component(modules = {DetailModule.class}, dependencies = {AppComponent.class})
public interface DetailComponent {

    void inject(AccountDetailActivity activity);

}
