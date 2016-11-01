package com.lht.qrcode.scan;
/** 
 * @ClassName: IScanResultHandler 
 * @Description: 扫描结果处理
 * @date 2016年3月15日 下午1:53:08
 *  
 * @author leobert.lan
 * @version 1.0
 */
public interface IScanResultHandler {
	
	void onSuccess(String result);
	
	void onFailure();
	
	void onTimeout();
	
	void onCancel();

}
