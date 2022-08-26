package com.leo.prb.entities;

import java.io.Serializable;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/2/15 16:58
 * Desc:
 */
@Entity
public class AccountTypeEntity implements Serializable {

    @Id(assignable = true)
    private Long id;

    private String guid;
    private String name;
    private String simpleName;

    public AccountTypeEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSimpleName() {
        return simpleName;
    }

    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
    }
}
