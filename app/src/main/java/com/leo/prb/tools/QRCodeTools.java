package com.leo.prb.tools;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/5/19 10:33
 * Desc:
 */
public class QRCodeTools {

    public QRCodeTools() {

    }

    /**
     * 二维码生成
     *
     * @param content 二维码内容
     * @param width   二维码宽度
     * @param height  二维码高度
     * @return 二维码bitmap
     */
    public Bitmap createQRCodeBitmap(@NonNull String content, int width, int height) {
        return createQRCodeBitmap(content, width, height, "UTF-8", ErrorCorrectionLevel.H, "1", Color.BLACK, Color.WHITE);
    }


    /**
     * 二维码生成
     *
     * @param content              二维码内容
     * @param width                二维码宽度
     * @param height               二维码高度
     * @param characterSet         编码
     * @param errorCorrectionLevel 容错率
     * @param margin               空白边距
     * @param colorBlack           黑色色块
     * @param colorWhite           白色色块
     * @return 二维码bitmap
     */
    public Bitmap createQRCodeBitmap(@NonNull String content, int width, int height, String characterSet,
                                     ErrorCorrectionLevel errorCorrectionLevel, String margin, int colorBlack,
                                     int colorWhite) {
        if (TextUtils.isEmpty(content)) {
            throw new InvalidParameterException("The 'content' parameter must not be null");
        }
        if (width < 0 || height < 0) {
            throw new InvalidParameterException("The 'width' or 'height' parameter must not be lower than 0");
        }
        try {
            Map<EncodeHintType, Object> hints = new HashMap<>();
            if (!TextUtils.isEmpty(characterSet)) {
                hints.put(EncodeHintType.CHARACTER_SET, characterSet);//字符集
            }
            if (errorCorrectionLevel != null) {
                hints.put(EncodeHintType.ERROR_CORRECTION, errorCorrectionLevel);//容错级别
            }
            if (!TextUtils.isEmpty(margin)) {
                hints.put(EncodeHintType.MARGIN, margin);//空白边距
            }
            BitMatrix bitMatrix = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            int[] pixels = new int[width * height];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if (bitMatrix.get(x, y)) {
                        pixels[y * width + x] = colorBlack;
                    } else {
                        pixels[y * width + x] = colorWhite;
                    }
                }
            }
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, width, 0, 0, width, height);

            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }

}
