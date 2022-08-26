package com.leo.prb.entities;

import java.io.Serializable;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/5/19 14:17
 * Desc:
 */
public class AccountJsonEntity implements Serializable {
    private Long id;

    private String account;
    private String password;
    private String desc;
    private Long createTime;

    private AccountTypeEntity accountType;

    public AccountJsonEntity() {
    }

    public AccountJsonEntity(AccountEntity accountEntity) {
        setId(accountEntity.getId());
        setAccount(accountEntity.getAccount());
        setPassword(accountEntity.getPassword());
        setDesc(accountEntity.getDesc());
        setCreateTime(accountEntity.getCreateTime());
        setAccountType(accountEntity.getAccountType().getTarget());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public AccountTypeEntity getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountTypeEntity accountType) {
        this.accountType = accountType;
    }


}
