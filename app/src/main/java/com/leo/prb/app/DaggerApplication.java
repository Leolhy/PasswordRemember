package com.leo.prb.app;

import android.app.Application;

import com.leo.prb.dagger.modules.AppModule;
import com.leo.prb.dagger.modules.BoxModule;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/2/15 17:32
 * Desc:
 */
public abstract class DaggerApplication extends Application {

    private AppModule appModule;
    private BoxModule boxModule;

    @Override
    public void onCreate() {
        super.onCreate();
        this.appModule = new AppModule(this);
        this.boxModule = new BoxModule();
    }

    public AppModule getAppModule() {
        return appModule;
    }

    public BoxModule getBoxModule() {
        return boxModule;
    }
}
