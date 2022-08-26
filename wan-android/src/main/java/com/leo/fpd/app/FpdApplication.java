package com.leo.fpd.app;

import com.leo.base.app.BaseDaggerApplication;
import com.leo.base.dagger.components.BaseAppComponent;
import com.leo.base.dagger.components.DaggerBaseAppComponent;
import com.leo.fpd.app.dagger.components.AppComponent;
import com.leo.fpd.app.dagger.components.DaggerAppComponent;
import com.leo.fpd.app.dagger.modules.AppModule;
import com.leo.fpd.app.dagger.modules.BoxModule;
import com.leo.fpd.objectBox.ObjectBoxTools;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/4 17:33
 * Desc:
 */
public class FpdApplication extends BaseDaggerApplication {

    @Override
    protected BaseAppComponent initBaseAppComponent() {
        return DaggerBaseAppComponent.builder()
                .baseAppModule(getAppModule())
                .build();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ObjectBoxTools.init(getApplicationContext());
    }

    public AppComponent getAppComponent(BaseAppComponent baseAppComponent) {
        return DaggerAppComponent.builder()
                .baseAppComponent(baseAppComponent)
                .appModule(new AppModule())
                .boxModule(new BoxModule())
                .build();
    }

}
