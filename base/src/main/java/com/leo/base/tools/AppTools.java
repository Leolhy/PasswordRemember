package com.leo.base.tools;

import android.content.Context;
import android.content.Intent;

import com.leo.base.activity.BaseDaggerVvmActivity;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/25 14:18
 * Desc:
 */
public class AppTools {

    public static <A extends BaseDaggerVvmActivity<?, ?>> void openActivity(Context context, Class<A> aClass) {
        Intent activityIntent = new Intent(context, aClass);
        context.startActivity(activityIntent);
    }


}
