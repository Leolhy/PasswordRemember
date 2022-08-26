package com.leo.fpd.api;

import com.leo.base.http.EntityResponse;
import com.leo.fpd.entities.UserEntity;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/5 16:26
 * Desc:
 */
public interface LoginApi {

    @POST("/user/login")
    Observable<EntityResponse<UserEntity>> loginIn(@Query("username") String username,
                                                   @Query("password") String password);

}
