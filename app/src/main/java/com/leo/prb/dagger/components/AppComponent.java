package com.leo.prb.dagger.components;

import android.app.Application;
import android.hardware.fingerprint.FingerprintManager;

import com.google.gson.Gson;
import com.jeremyliao.liveeventbus.core.Observable;
import com.leo.prb.dagger.modules.AppModule;
import com.leo.prb.dagger.modules.BoxModule;
import com.leo.prb.entities.AccountEntity;
import com.leo.prb.entities.AccountTypeEntity;
import com.leo.prb.event.AppEvent;
import com.leo.prb.tools.EncryptTools;
import com.leo.prb.tools.QRCodeTools;

import javax.inject.Singleton;

import dagger.Component;
import io.objectbox.Box;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/2/15 17:34
 * Desc:
 */
@Singleton
@Component(modules = {AppModule.class, BoxModule.class})
public interface AppComponent {

    Application application();

    Box<AccountTypeEntity> accountTypeBox();

    Box<AccountEntity> accountBox();

    EncryptTools EncryptTools();

    Gson gson();

    QRCodeTools QRCodeTools();

    FingerprintManager FingerprintManager();

    Observable<AppEvent> appEventBus();
}
