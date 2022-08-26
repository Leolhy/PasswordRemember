package com.leo.base.http;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/5 16:36
 * Desc:
 */
public class EntityResponse<T> extends BaseResponse {
    
    /**
     * data : {}
     * errorCode : 0
     * errorMsg :
     */

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
