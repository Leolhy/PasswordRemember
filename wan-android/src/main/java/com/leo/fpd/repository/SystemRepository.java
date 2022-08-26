package com.leo.fpd.repository;

import com.leo.fpd.apiImpl.SystemApiImpl;

import javax.inject.Inject;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/17 11:50
 * Desc:
 */
public class SystemRepository {

    private final SystemApiImpl systemApi;

    @Inject
    public SystemRepository(SystemApiImpl systemApi) {
        this.systemApi = systemApi;
    }

    public SystemApiImpl getSystemApi() {
        return systemApi;
    }
}
