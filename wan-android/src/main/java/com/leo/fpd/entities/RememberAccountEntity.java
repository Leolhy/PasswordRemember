package com.leo.fpd.entities;

import java.io.Serializable;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToOne;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/9 15:42
 * Desc:
 */
@Entity
public class RememberAccountEntity implements Serializable {

    @Id(assignable = true)
    private Long id;
    private String account;
    private String password;
    private Long loginTime;

    private ToOne<UserEntity> userEntity = new ToOne<>(this, RememberAccountEntity_.userEntity);

    public RememberAccountEntity() {
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

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    public ToOne<UserEntity> getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(ToOne<UserEntity> userEntity) {
        this.userEntity = userEntity;
    }
}
