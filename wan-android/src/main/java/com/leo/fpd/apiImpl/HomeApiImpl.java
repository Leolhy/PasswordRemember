package com.leo.fpd.apiImpl;

import com.leo.base.http.ListResponse;
import com.leo.fpd.api.HomeApi;
import com.leo.fpd.entities.BannerEntity;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/11 16:11
 * Desc:
 */
public class HomeApiImpl extends BaseWanAndroidApi {

    @Inject
    public HomeApiImpl() {
        super();
    }

    public Observable<ListResponse<BannerEntity>> loadBannerList() {
        return createServer(HomeApi.class)
                .getBannerList()
                .compose(ioMain());
    }

}
