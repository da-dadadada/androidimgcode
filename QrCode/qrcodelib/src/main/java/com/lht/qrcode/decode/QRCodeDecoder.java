package com.lht.qrcode.decode;

import android.graphics.Bitmap;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;

import java.util.HashMap;

/**
 * <p><b>Package</b> com.lht.qrcode.decode
 * <p><b>Project</b> QrCode
 * <p><b>Classname</b> QRCodeDecoder
 * <p><b>Description</b>: TODO
 * <p>Created by leobert on 2016/11/1.
 */
public class QRCodeDecoder {

    /**
     * 小心 oom问题，需要优化
     * @param bitmap
     * @return
     */
    public static String decode(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        HashMap<DecodeHintType, String> hints = new HashMap<>();
        hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
        RGBLuminanceSource source = new RGBLuminanceSource(bitmap);
        BinaryBitmap bitmap1 = new BinaryBitmap(new HybridBinarizer(source));
        QRCodeReader reader = new QRCodeReader();

        try {
            Result result = reader.decode(bitmap1, hints);
            return result.getText();
        } catch (ChecksumException | FormatException | NotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
