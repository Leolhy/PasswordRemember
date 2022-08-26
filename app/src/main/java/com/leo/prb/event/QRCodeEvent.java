package com.leo.prb.event;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/5/19 16:35
 * Desc:
 */
public class QRCodeEvent extends AppEvent {
    private String qrcodeResult;

    public QRCodeEvent(String qrcodeResult) {
        this.qrcodeResult = qrcodeResult;
    }

    public String getQrcodeResult() {
        return qrcodeResult;
    }

    public void setQrcodeResult(String qrcodeResult) {
        this.qrcodeResult = qrcodeResult;
    }
}
