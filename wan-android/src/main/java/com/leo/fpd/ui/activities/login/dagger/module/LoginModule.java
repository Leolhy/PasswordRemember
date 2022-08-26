package com.leo.fpd.ui.activities.login.dagger.module;

import android.view.LayoutInflater;

import com.leo.base.dagger.annotation.ActivityScope;
import com.leo.base.dagger.modules.BaseActivityModule;
import com.leo.fpd.databinding.ActivityLoginBinding;

import dagger.Module;
import dagger.Provides;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/5 16:14
 * Desc:
 */
@Module
public class LoginModule extends BaseActivityModule {

    public LoginModule(LayoutInflater inflater) {
        super(inflater);
    }

    @ActivityScope
    @Provides
    public ActivityLoginBinding provideActivityLoginBinding() {
        return ActivityLoginBinding.inflate(inflater);
    }

}
