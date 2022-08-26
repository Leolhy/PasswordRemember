package com.leo.prb.event;

import com.leo.prb.entities.AccountEntity;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/4/13 15:47
 * Desc:
 */
public class AccountEvent extends AppEvent {
    private AccountEntity accountEntity;

    public AccountEvent(AccountEntity accountEntity) {
        this.accountEntity = accountEntity;
    }

    public AccountEntity getAccountEntity() {
        return accountEntity;
    }

    public void setAccountEntity(AccountEntity accountEntity) {
        this.accountEntity = accountEntity;
    }
}
