package com.leo.fpd.ui.fragments.project.dagger.component;

import com.leo.base.dagger.annotation.FragmentScope;
import com.leo.fpd.app.dagger.components.AppComponent;
import com.leo.fpd.ui.fragments.project.dagger.module.ProjectModule;
import com.leo.fpd.ui.fragments.project.view.ProjectFragment;

import dagger.Component;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/10 14:29
 * Desc:
 */
@FragmentScope
@Component(dependencies = {AppComponent.class}, modules = {ProjectModule.class})
public interface ProjectComponent {

    void inject(ProjectFragment fragment);

}
