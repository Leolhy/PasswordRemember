package com.leo.base.dagger.modules;

import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/10 14:08
 * Desc:
 */
public class BaseFragmentModule {

    protected LayoutInflater inflater;
    protected ViewGroup container;

    public BaseFragmentModule(LayoutInflater inflater, ViewGroup container) {
        this.inflater = inflater;
        this.container = container;
    }
}
