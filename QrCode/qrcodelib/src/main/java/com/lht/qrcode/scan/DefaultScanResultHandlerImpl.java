package com.lht.qrcode.scan;

import android.util.Log;

import com.lht.qrcode.scan.IScanResultHandler;

/** 
 * @ClassName: DefaultScanResultHandlerImpl 
 * @Description: 默认的处理类
 * @date 2016年3月15日 下午1:56:15
 *  
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

}
