package com.leo.fpd.repository;


import com.leo.fpd.apiImpl.LoginApiImpl;
import com.leo.fpd.entities.UserEntity;

import javax.inject.Inject;

import io.objectbox.Box;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/5 17:19
 * Desc:
 */
public class UserRepository {
    private final LoginApiImpl loginApi;
    private final Box<UserEntity> userBox;

    @Inject
    public UserRepository(LoginApiImpl loginApi, Box<UserEntity> userBox) {
        this.loginApi = loginApi;
        this.userBox = userBox;
    }

    public Box<UserEntity> getUserBox() {
        return userBox;
    }

    public LoginApiImpl getLoginApi() {
        return loginApi;
    }



}
