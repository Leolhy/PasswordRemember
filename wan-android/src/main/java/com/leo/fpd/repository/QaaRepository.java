package com.leo.fpd.repository;

import com.leo.fpd.apiImpl.QaaApiImpl;

import javax.inject.Inject;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/10 16:40
 * Desc:
 */
public class QaaRepository {

    private final QaaApiImpl qaaApi;

    @Inject
    public QaaRepository(QaaApiImpl qaaApi) {
        this.qaaApi = qaaApi;
    }

    public QaaApiImpl getQaaApi() {
        return qaaApi;
    }
}
