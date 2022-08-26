package com.leo.fpd.ui.fragments.mine.dagger.component;

import com.leo.base.dagger.annotation.FragmentScope;
import com.leo.fpd.app.dagger.components.AppComponent;
import com.leo.fpd.ui.fragments.mine.dagger.module.MineModule;
import com.leo.fpd.ui.fragments.mine.view.MineFragment;

import dagger.Component;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/10 14:15
 * Desc:
 */
@FragmentScope
@Component(dependencies = {AppComponent.class}, modules = {MineModule.class})
public interface MineComponent {

    void inject(MineFragment fragment);

}
