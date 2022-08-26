package com.leo.prb.tools;

import androidx.annotation.NonNull;
import android.os.CancellationSignal;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/2/16 16:37
 * Desc:
 */
public interface IBiometricPrompt {
    void authenticate(@NonNull CancellationSignal cancel);
}
