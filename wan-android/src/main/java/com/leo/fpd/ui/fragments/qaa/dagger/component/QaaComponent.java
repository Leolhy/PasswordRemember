package com.leo.fpd.ui.fragments.qaa.dagger.component;

import com.leo.base.dagger.annotation.FragmentScope;
import com.leo.fpd.app.dagger.components.AppComponent;
import com.leo.fpd.repository.QaaRepository;
import com.leo.fpd.ui.fragments.qaa.dagger.module.QaaModule;
import com.leo.fpd.ui.fragments.qaa.view.QaaFragment;

import dagger.Component;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/10 16:03
 * Desc:
 */
@FragmentScope
@Component(dependencies = {AppComponent.class}, modules = {QaaModule.class})
public interface QaaComponent {

    void inject(QaaFragment fragment);

    QaaRepository qaaRepository();

}
