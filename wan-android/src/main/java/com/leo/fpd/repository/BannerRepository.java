package com.leo.fpd.repository;

import com.leo.fpd.apiImpl.HomeApiImpl;

import javax.inject.Inject;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/11 16:14
 * Desc:
 */
public class BannerRepository {

    private final HomeApiImpl homeApi;

    @Inject
    public BannerRepository(HomeApiImpl homeApi) {
        this.homeApi = homeApi;
    }

    public HomeApiImpl getHomeApi() {
        return homeApi;
    }
}
