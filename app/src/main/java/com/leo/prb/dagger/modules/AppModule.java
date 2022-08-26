package com.leo.prb.dagger.modules;

import android.app.Application;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.jeremyliao.liveeventbus.LiveEventBus;
import com.jeremyliao.liveeventbus.core.Observable;
import com.leo.prb.event.AppEvent;
import com.leo.prb.tools.EncryptTools;
import com.leo.prb.tools.QRCodeTools;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/2/15 17:35
 * Desc:
 */
@Module
public class AppModule {

    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Singleton
    @Provides
    public Application provideApplication() {
        return this.application;
    }

    @Singleton
    @Provides
    public Gson provideGson() {
        return new Gson();
    }

    @Singleton
    @Provides
    public FingerprintManager provideFingerprintManager(@NonNull Application application) {
        if (application.getPackageManager().hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)) {
            return application.getSystemService(FingerprintManager.class);
        } else {
            return null;
        }
    }

    @Singleton
    @Provides
    public EncryptTools provideEncryptTools(Application application) {
        return EncryptTools.getInstance(application);
    }

    @Singleton
    @Provides
    public Observable<AppEvent> provideEventBus() {
        return LiveEventBus.get("AppLiveEventBus", AppEvent.class);
    }

    @Singleton
    @Provides
    public QRCodeTools provideQRCodeTools() {
        return new QRCodeTools();
    }

}
