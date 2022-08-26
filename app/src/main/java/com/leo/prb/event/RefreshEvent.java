package com.leo.prb.event;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/3/4 16:59
 * Desc:
 */
public class RefreshEvent extends AppEvent {
    private String target;

    public RefreshEvent(String target) {
        this.target = target;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
