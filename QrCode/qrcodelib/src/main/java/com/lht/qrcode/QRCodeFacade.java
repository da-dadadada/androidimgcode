package com.lht.qrcode;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;

import com.lht.qrcode.decode.QRCodeDecoder;
import com.lht.qrcode.generate.QRCodeGenerator;
import com.lht.qrcode.scan.IScanResultHandler;
import com.lht.qrcode.scan.ScanActivity;
import com.lht.qrcode.scan.ScanProps;

import java.lang.ref.WeakReference;

/**
 * <p><b>Package</b> com.lht.qrcode
 * <p><b>Project</b> QrCode
 * <p><b>Classname</b> QRCodeFacade
 * <p><b>Description</b>: TODO
 * <p>Created by leobert on 2016/11/1.
 */

public class QRCodeFacade {

    public void scan(Context context, IScanResultHandler handler) {
        if (context == null) {
            return;
        }
        String key = context.getClass().getName();
        ScanProps.addScanResultHandler(key, handler);
        Intent intent = new Intent(context, ScanActivity.class);
        intent.putExtra(ScanActivity.KEY_HANDLER, key);
        context.startActivity(intent);

    }

    public Bitmap generate(String src) {
        return QRCodeGenerator.generate(src);
    }

    public Bitmap generate(String src, int squareLength) {
        return QRCodeGenerator.generate(src, squareLength);
    }

    public String decode(Bitmap bitmap) {
        return QRCodeDecoder.decode(bitmap);
    }


}
