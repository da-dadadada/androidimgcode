package com.lht.qrcode.scan;

import android.util.Log;

import com.zbar.lib.decode.InactivityTimer;

import java.util.HashMap;

/**
 * ScanProps 关键配置
 *
 * @author leobert.lan
 * @version 1.0
 */
public class ScanProps {

    private static final String tag = "ScanProps";

    private static int scanMaxSeconds = -1;

    private static HashMap<String, IScanResultHandler> scanHandlers = new HashMap<>();

    public static int getScanMaxSeconds() {
        return scanMaxSeconds;
    }

    public static void setScanMaxSeconds(int scanMaxSeconds) {
        if (scanMaxSeconds > 0) {
            ScanProps.scanMaxSeconds = scanMaxSeconds;
            InactivityTimer.getIScanMaxSecondsSetListener().onScanMaxSecondsSet(scanMaxSeconds);
        } else {
            Log.e(tag, "you should give a int larger than zero");
        }

    }

    public static IScanResultHandler getScanResultHandler(String key) {
        if (scanHandlers.containsKey(key)) {
            return scanHandlers.remove(key);
        }
        return null;
    }

    public static void addScanResultHandler(String key, IScanResultHandler scanResultHandler) {
        scanHandlers.put(key, scanResultHandler);
    }

}
