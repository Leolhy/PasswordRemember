package com.leo.prb.tools;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.CancellationSignal;

import javax.inject.Inject;

import dagger.Lazy;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/2/16 16:32
 * Desc:
 */
@SuppressLint("ObsoleteSdkInt")
public class BiometricPromptManager {

    private IBiometricPrompt mImpl;

    @Inject
    public BiometricPromptManager(BiometricPromptApi23 promptApi23, BiometricPromptApi28 promptApi28) {
        if (isAboveApi28()) {
            mImpl = promptApi28;
        } else if (isAboveApi23()) {
            mImpl = promptApi23;
        }
    }

    public void startAuthenticate() {
        if (mImpl != null) {
            CancellationSignal cancellationSignal = new CancellationSignal();
            mImpl.authenticate(cancellationSignal);
        }
    }


    private boolean isAboveApi23() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    private boolean isAboveApi28() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.P;
    }


}
