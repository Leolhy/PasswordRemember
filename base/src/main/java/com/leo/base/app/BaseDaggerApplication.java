package com.leo.base.app;

import android.app.Application;

import com.leo.base.dagger.components.BaseAppComponent;
import com.leo.base.dagger.modules.BaseAppModule;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/7/29 16:42
 * Desc:
 */
public abstract class BaseDaggerApplication extends Application {
    private BaseAppModule appModule;
    private BaseAppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.appModule = new BaseAppModule(this);
        this.appComponent = initBaseAppComponent();
    }

    protected abstract BaseAppComponent initBaseAppComponent();

    public BaseAppModule getAppModule() {
        return appModule;
    }

    public BaseAppComponent getBaseAppComponent() {
        return appComponent;
    }
}
