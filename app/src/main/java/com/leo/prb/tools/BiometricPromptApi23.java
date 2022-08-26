package com.leo.prb.tools;

import android.annotation.SuppressLint;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;

import androidx.annotation.NonNull;

import javax.inject.Inject;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/2/16 16:42
 * Desc:
 */
@SuppressLint("ObsoleteSdkInt")
public class BiometricPromptApi23 implements IBiometricPrompt {

    private final FingerprintManager fingerprintManager;

    @Inject
    EncryptTools encryptTools;

    @Inject
    public BiometricPromptApi23(FingerprintManager fingerprintManager) {
        this.fingerprintManager = fingerprintManager;
    }

    @Override
    public void authenticate(@NonNull CancellationSignal cancel) {
        if (fingerprintManager == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            fingerprintManager.authenticate(new FingerprintManager.CryptoObject(encryptTools.getCipher("HoyangLau")),
                    cancel, 0, new FingerprintManager.AuthenticationCallback() {
                        @Override
                        public void onAuthenticationError(int errorCode, CharSequence errString) {
                            super.onAuthenticationError(errorCode, errString);
                        }

                        @Override
                        public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
                            super.onAuthenticationHelp(helpCode, helpString);
                        }

                        @Override
                        public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
                            super.onAuthenticationSucceeded(result);
                        }

                        @Override
                        public void onAuthenticationFailed() {
                            super.onAuthenticationFailed();
                        }
                    }, null);

        }
    }
}
