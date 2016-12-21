package com.lht.qrcode.scan;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * ScanResultReceiver 接收扫描结果
 * @author leobert.lan
 * @version 1.0
 */
public class ScanResultReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(ScanActivity.BROADCAST_ACTION)) {
			// 处理情况
			int scanResultCode = intent.getIntExtra(ScanActivity.RESULT_CODE,
					ScanActivity.SCAN_FAILURE);
			String data = intent.getStringExtra(ScanActivity.RESULT);
			String key = intent.getStringExtra(ScanActivity.KEY_HANDLER);
			switch (scanResultCode) {
			case ScanActivity.SCAN_OK:
				getHandler(key).onSuccess(data);
				break;
			case ScanActivity.SCAN_FAILURE:
				getHandler(key).onFailure();
				break;
			case ScanActivity.SCAN_TIMEOUT:
				getHandler(key).onTimeout();
				break;
			case ScanActivity.SCAN_CANCEL:
				getHandler(key).onCancel();
			default:
				break;
			}
		}
	}

	private IScanResultHandler getHandler(String key) {
		IScanResultHandler handler = ScanProps.getScanResultHandler(key);

		return handler == null ? new DefaultScanResultHandlerImpl() : handler;
	}

}
