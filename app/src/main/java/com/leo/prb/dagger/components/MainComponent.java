package com.leo.prb.dagger.components;

import com.leo.prb.dagger.annotation.ActivityScope;
import com.leo.prb.dagger.modules.MainModule;
import com.leo.prb.mvvm.main.view.MainActivity;
import com.leo.prb.tools.BiometricPromptApi23;
import com.leo.prb.tools.BiometricPromptApi28;

import dagger.Component;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/2/16 11:27
 * Desc:
 */
@ActivityScope
@Component(dependencies = {AppComponent.class}, modules = {MainModule.class})
public interface MainComponent {

    void inject(MainActivity activity);

}
