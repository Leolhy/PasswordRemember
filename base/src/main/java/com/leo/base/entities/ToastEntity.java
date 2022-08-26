package com.leo.base.entities;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/9 14:45
 * Desc:
 */
public class ToastEntity {
    private String toastContent;
    private boolean hideLoading;

    public ToastEntity(String toastContent) {
        this(toastContent, true);
    }

    public ToastEntity(String toastContent, boolean hideLoading) {
        this.toastContent = toastContent;
        this.hideLoading = hideLoading;
    }

    public String getToastContent() {
        return toastContent;
    }

    public void setToastContent(String toastContent) {
        this.toastContent = toastContent;
    }

    public boolean isHideLoading() {
        return hideLoading;
    }

    public void setHideLoading(boolean hideLoading) {
        this.hideLoading = hideLoading;
    }
}
