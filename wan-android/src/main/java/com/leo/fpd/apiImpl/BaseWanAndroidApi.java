package com.leo.fpd.apiImpl;

import com.leo.base.retrofit.RetrofitCenter;
import com.leo.fpd.constant.AppConstant;
import com.leo.fpd.http.CookieInterceptor;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/5 16:30
 * Desc:
 */
public class BaseWanAndroidApi extends RetrofitCenter {

    public BaseWanAndroidApi() {
        init();
    }

    @Override
    protected String getBaseUrl() {
        return AppConstant.BASE_URL;
    }

}
