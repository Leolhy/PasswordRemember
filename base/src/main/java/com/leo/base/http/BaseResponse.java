package com.leo.base.http;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/5 16:36
 * Desc:
 */
public class BaseResponse {

    private int errorCode;
    private String errorMsg;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    }
