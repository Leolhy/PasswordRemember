package com.leo.fpd.apiImpl;

import com.leo.base.http.EntityResponse;
import com.leo.fpd.api.LoginApi;
import com.leo.fpd.entities.UserEntity;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/5 16:27
 * Desc:
 */
public class LoginApiImpl extends BaseWanAndroidApi {

    @Inject
    public LoginApiImpl() {
        super();
    }

    public Observable<EntityResponse<UserEntity>> loginIn(String username, String password) {
        return createServer(LoginApi.class)
                .loginIn(username, password)
                .compose(ioMain());
    }

}
