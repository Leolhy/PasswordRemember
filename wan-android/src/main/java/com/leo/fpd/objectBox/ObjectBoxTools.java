package com.leo.fpd.objectBox;

import android.content.Context;


import com.leo.fpd.entities.MyObjectBox;

import io.objectbox.BoxStore;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/7/29 16:26
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
