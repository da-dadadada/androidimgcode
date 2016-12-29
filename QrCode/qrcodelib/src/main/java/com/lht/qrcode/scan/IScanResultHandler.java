package com.lht.qrcode.scan;
/**
 *  IScanResultHandler 扫描结果处理接口
 * @author leobert.lan
 * @version 1.0
 */
public interface IScanResultHandler {
	
	void onSuccess(String result);
	
	void onFailure();
	
	void onTimeout();
	
	void onCancel();

	void onManualGrantPermissionRefuse();

}
