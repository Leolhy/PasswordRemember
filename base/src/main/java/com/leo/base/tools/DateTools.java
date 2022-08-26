package com.leo.base.tools;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/10 17:04
 * Desc:
 */
public class DateTools {

    public static String getStringDate(Long dateTime) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date(dateTime));
    }

}
