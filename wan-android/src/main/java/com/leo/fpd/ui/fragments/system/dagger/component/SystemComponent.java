package com.leo.fpd.ui.fragments.system.dagger.component;

import com.leo.base.dagger.annotation.FragmentScope;
import com.leo.fpd.app.dagger.components.AppComponent;
import com.leo.fpd.ui.fragments.system.dagger.module.SystemModule;
import com.leo.fpd.ui.fragments.system.view.SystemFragment;

import dagger.Component;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/10 14:23
 * Desc:
 */
@FragmentScope
@Component(dependencies = {AppComponent.class}, modules = {SystemModule.class})
public interface SystemComponent {

    void inject(SystemFragment fragment);

}
