package com.leo.fpd.ui.fragments.home.dagger.component;

import com.leo.base.dagger.annotation.FragmentScope;
import com.leo.fpd.app.dagger.components.AppComponent;
import com.leo.fpd.ui.fragments.home.dagger.module.HomeModule;
import com.leo.fpd.ui.fragments.home.view.HomeFragment;

import dagger.Component;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/10 11:49
 * Desc:
 */
@FragmentScope
@Component(dependencies = {AppComponent.class}, modules = {HomeModule.class})
public interface HomeComponent {

    void inject(HomeFragment fragment);
}
