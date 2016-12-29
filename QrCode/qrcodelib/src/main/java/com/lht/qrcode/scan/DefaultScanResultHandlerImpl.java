package com.lht.qrcode.scan;

import android.util.Log;

/**
 * 默认的扫码结果处理类
 * @author leobert.lan
 * @version 1.0
 */
public final class DefaultScanResultHandlerImpl implements IScanResultHandler {
	
	private static final String tag = "defaulthandler";

	@Override
	public void onSuccess(String result) {
		Log.d(tag, "scan result is:\r\n"+result);
	}

	@Override
	public void onFailure() {
		Log.d(tag, "scan failure,maybe not support encoding type");
	}

	@Override
	public void onTimeout() {
		Log.d(tag, "scan timeout,default time is 30s,have you set a very short one?");
	}

	@Override
	public void onCancel() {
		Log.d(tag, "scan canceled by user");
	}

	@Override
	public void onManualGrantPermissionRefuse() {
		Log.d(tag, "refuse camera grant");
	}

}
