package com.leo.prb.entities;

import java.io.Serializable;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToOne;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/2/22 16:34
 * Desc:
 */
@Entity
public class AccountEntity implements Serializable {

    @Id(assignable = true)
    private Long id;

    private String account;
    private String password;
    private String desc;
    private Long createTime;

    private ToOne<AccountTypeEntity> accountType;

    public AccountEntity() {
    }

    public AccountEntity(AccountJsonEntity accountJsonEntity) {
        setAccount(accountJsonEntity.getAccount());
        setPassword(accountJsonEntity.getPassword());
        setDesc(accountJsonEntity.getDesc());
        setCreateTime(System.currentTimeMillis());
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

    public ToOne<AccountTypeEntity> getAccountType() {
        return accountType;
    }

    public void setAccountType(ToOne<AccountTypeEntity> accountType) {
        this.accountType = accountType;
    }
}
