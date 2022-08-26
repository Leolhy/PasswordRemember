package com.leo.prb.dagger.components;

import com.leo.prb.dagger.annotation.ActivityScope;
import com.leo.prb.dagger.modules.ScanModule;
import com.leo.prb.mvvm.scan.view.ScanActivity;

import dagger.Component;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/5/19 14:51
 * Desc:
 */
@ActivityScope
@Component(modules = {ScanModule.class}, dependencies = {AppComponent.class})
public interface ScanComponent {

    void inject(ScanActivity activity);

}
