package com.leo.fpd.ui.activities.login.dagger.component;

import com.leo.base.dagger.annotation.ActivityScope;
import com.leo.fpd.app.dagger.components.AppComponent;
import com.leo.fpd.repository.RememberAccountRepository;
import com.leo.fpd.repository.UserRepository;
import com.leo.fpd.ui.activities.login.dagger.module.LoginModule;
import com.leo.fpd.ui.activities.login.view.LoginActivity;

import dagger.Component;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/5 16:13
 * Desc:
 */
@ActivityScope
@Component(dependencies = {AppComponent.class}, modules = {LoginModule.class})
public interface LoginComponent {

    void inject(LoginActivity activity);

    UserRepository userRepository();

    RememberAccountRepository rememberAccountRepository();
}
