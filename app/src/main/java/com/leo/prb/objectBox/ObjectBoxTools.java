package com.leo.prb.objectBox;

import android.content.Context;

import com.leo.prb.entities.MyObjectBox;

import io.objectbox.BoxStore;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/2/15 17:29
 * Desc:
 */
public class ObjectBoxTools {

    private static BoxStore boxStore;

    public static void init(Context context) {
        boxStore = MyObjectBox.builder()
                .androidContext(context.getApplicationContext())
                .build();
    }

    public static BoxStore get() {
        return boxStore;
    }
}
