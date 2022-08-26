package com.leo.base.dagger.modules;

import android.view.LayoutInflater;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/10 14:07
 * Desc:
 */
public class BaseActivityModule {
    protected LayoutInflater inflater;

    public BaseActivityModule(LayoutInflater inflater) {
        this.inflater = inflater;
    }
}
