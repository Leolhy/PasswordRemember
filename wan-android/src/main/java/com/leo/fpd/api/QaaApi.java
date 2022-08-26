package com.leo.fpd.api;

import com.leo.base.http.PageResponse;
import com.leo.fpd.entities.QaaEntity;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/10 16:29
 * Desc:
 */
public interface QaaApi {

    @GET("/wenda/list/{page}/json")
    Observable<PageResponse<QaaEntity>> getQaaPage(@Path("page") int page);

}
