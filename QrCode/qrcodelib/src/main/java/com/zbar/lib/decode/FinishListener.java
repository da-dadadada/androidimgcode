package com.zbar.lib.decode;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;

import com.lht.qrcode.scan.ScanActivity;


/** 
 * @ClassName: FinishListener 
 * @Description: TODO
 * @date 2016年3月11日 上午10:52:47
 *  
 * @author leobert.lan
 * @version 1.0 
 * @since JDK 1.6 
 */
public final class FinishListener implements DialogInterface.OnClickListener,
		DialogInterface.OnCancelListener, Runnable {

	private final Activity activityToFinish;

	public FinishListener(Activity activityToFinish) {
		this.activityToFinish = activityToFinish;
	}

	@Override
	public void onCancel(DialogInterface dialogInterface) {
		run();
	}

	@Override
	public void onClick(DialogInterface dialogInterface, int i) {
		run();
	}

	@Override
	public void run() {
		Intent intent = new Intent();
		intent.setAction(ScanActivity.BROADCAST_ACTION);
		intent.putExtra(ScanActivity.RESULT_CODE,
				ScanActivity.SCAN_TIMEOUT);
		intent.putExtra(ScanActivity.RESULT, "");
		activityToFinish.finish();
	}

}
