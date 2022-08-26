package com.leo.fpd.api;

import com.leo.base.http.ListResponse;
import com.leo.fpd.entities.BannerEntity;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/11 15:50
 * Desc:
 */
public interface HomeApi {

    @GET("/banner/json")
    Observable<ListResponse<BannerEntity>> getBannerList();

}
