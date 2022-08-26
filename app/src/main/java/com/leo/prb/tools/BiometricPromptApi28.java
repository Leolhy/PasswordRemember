package com.leo.prb.tools;

import android.app.Application;
import android.content.DialogInterface;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import android.os.CancellationSignal;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.concurrent.Executors;

import javax.inject.Inject;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/2/16 16:42
 * Desc:
 */
public class BiometricPromptApi28 implements IBiometricPrompt {
    private static final String TAG = BiometricPromptApi28.class.getSimpleName();
    private final Application application;

    @Inject
    EncryptTools encryptTools;

    @Inject
    public BiometricPromptApi28(Application application) {
        this.application = application;
    }


    @Override
    public void authenticate(@NonNull CancellationSignal cancel) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            BiometricPrompt biometricPrompt = new BiometricPrompt.Builder(application.getApplicationContext())
                    .setTitle("Verification")
                    .setDescription("Verify fingerprint to continue")
                    .setSubtitle("")
                    .setNegativeButton("使用密码", Executors.newSingleThreadExecutor(), (dialog, which) -> {
                        cancel.throwIfCanceled();
                    })
                    .build();
            biometricPrompt.authenticate(new BiometricPrompt.CryptoObject(encryptTools.getCipher("HoyangLau")),
                    cancel, Executors.newSingleThreadExecutor(), new BiometricPrompt.AuthenticationCallback() {
                        @Override
                        public void onAuthenticationError(int errorCode, CharSequence errString) {
                            super.onAuthenticationError(errorCode, errString);
                            Log.e(TAG, "onAuthenticationError: " + errString);

                        }

                        @Override
                        public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
                            super.onAuthenticationHelp(helpCode, helpString);
                            Log.e(TAG, "onAuthenticationHelp: " + helpString);
                        }

                        @Override
                        public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                            super.onAuthenticationSucceeded(result);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                                Log.e(TAG, "onAuthenticationSucceeded: " + result.getAuthenticationType());
                            }
                        }

                        @Override
                        public void onAuthenticationFailed() {
                            super.onAuthenticationFailed();
                            Log.e(TAG, "onAuthenticationFailed: failed");
                        }
                    });

        }

    }
}
