package com.leo.fpd.api;

import com.leo.base.http.ListResponse;
import com.leo.fpd.entities.SystemClassEntity;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/17 11:33
 * Desc:
 */
public interface SystemApi {

    @GET("/tree/json")
    public Observable<ListResponse<SystemClassEntity>> getSystemClassTree();

}
