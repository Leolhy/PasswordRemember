package com.leo.fpd.apiImpl;

import com.leo.base.http.PageResponse;
import com.leo.fpd.api.QaaApi;
import com.leo.fpd.entities.QaaEntity;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/10 16:36
 * Desc:
 */
public class QaaApiImpl extends BaseWanAndroidApi {

    @Inject
    public QaaApiImpl() {
        super();
    }

    public Observable<PageResponse<QaaEntity>> getQaaPage(int page) {
        return createServer(QaaApi.class)
                .getQaaPage(page)
                .compose(ioMain());
    }

}
