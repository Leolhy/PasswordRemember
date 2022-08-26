package com.leo.fpd.repository;

import com.leo.fpd.entities.RememberAccountEntity;

import javax.inject.Inject;

import io.objectbox.Box;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/9 15:56
 * Desc:
 */
public class RememberAccountRepository {
    private final Box<RememberAccountEntity> rememberAccountBox;

    @Inject
    public RememberAccountRepository(Box<RememberAccountEntity> rememberAccountBox) {
        this.rememberAccountBox = rememberAccountBox;
    }

    public Box<RememberAccountEntity> getRememberAccountBox() {
        return rememberAccountBox;
    }
}
