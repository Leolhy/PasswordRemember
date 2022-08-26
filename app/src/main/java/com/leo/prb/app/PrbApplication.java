package com.leo.prb.app;

import com.leo.prb.dagger.components.AppComponent;
import com.leo.prb.dagger.components.DaggerAppComponent;
import com.leo.prb.objectBox.ObjectBoxTools;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/2/15 17:30
 * Desc:
 */
public class PrbApplication extends DaggerApplication {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(getAppModule())
                .boxModule(getBoxModule())
                .build();
        ObjectBoxTools.init(getApplicationContext());
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
