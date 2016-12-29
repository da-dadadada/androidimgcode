package com.lht.qrcode.scan;

import android.content.Intent;
import android.view.SurfaceHolder.Callback;

import com.zbar.lib.CaptureActivity;

public class ScanActivity extends CaptureActivity implements Callback {
    public static final String KEY_HANDLER = "_handler";

    @Override
    protected void onManualGrantPermissionRefuse() {
        Intent intent = new Intent();
        intent.setAction(BROADCAST_ACTION);
        intent.putExtra(RESULT_CODE, SCAN_CAMERA_GRANT_REFUSE);
        intent.putExtra(RESULT, "");
        intent.putExtra(KEY_HANDLER,getIntent().getStringExtra(KEY_HANDLER));
        sendBroadcast(intent);
    }

    @Override
    public void handleDecode(String resultString) {
        super.handleDecode(resultString);
        if (resultString.equals("")) {
            Intent intent = new Intent();
            intent.setAction(BROADCAST_ACTION);
            intent.putExtra(RESULT_CODE, SCAN_FAILURE);
            intent.putExtra(RESULT, resultString);
            intent.putExtra(KEY_HANDLER,getIntent().getStringExtra(KEY_HANDLER));
            sendBroadcast(intent);
        } else {
            Intent intent = new Intent();
            intent.setAction(BROADCAST_ACTION);
            intent.putExtra(RESULT_CODE, SCAN_OK);
            intent.putExtra(RESULT, resultString);
            intent.putExtra(KEY_HANDLER,getIntent().getStringExtra(KEY_HANDLER));
            sendBroadcast(intent);
        }
        this.finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.setAction(BROADCAST_ACTION);
        intent.putExtra(RESULT_CODE, SCAN_CANCEL);
        intent.putExtra(RESULT, "");
        intent.putExtra(KEY_HANDLER,getIntent().getStringExtra(KEY_HANDLER));
        sendBroadcast(intent);
        super.onBackPressed();

    }

    public static final String BROADCAST_ACTION = "individual.leoebrt.codescan.broadcastresult";

    public static final String RESULT = "scan_result";

    public static final String RESULT_CODE = "scan.resultcode";

    public static final int SCAN_OK = 1;

    public static final int SCAN_FAILURE = 2;

    public static final int SCAN_TIMEOUT = 3;

    public static final int SCAN_CANCEL = 4;

    /**
     * 用户拒绝进行手动授权
     */
    public static final int SCAN_CAMERA_GRANT_REFUSE = 5;

}