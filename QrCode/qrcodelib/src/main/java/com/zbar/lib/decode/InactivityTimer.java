package com.zbar.lib.decode;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import android.app.Activity;

/** 
 * @ClassName: InactivityTimer 
 * @Description: 倒计时类，当计时结束没有完成扫描即关闭，返回扫描超时
 * @date 2016年3月11日 上午10:53:14
 *  
 * @author leobert.lan
 * @version 1.0 
 */
public final class InactivityTimer {

	private static int INACTIVITY_DELAY_SECONDS = 30;

	private final ScheduledExecutorService inactivityTimer = Executors.newSingleThreadScheduledExecutor(new DaemonThreadFactory());
	private final Activity activity;
	private ScheduledFuture<?> inactivityFuture = null;

	public InactivityTimer(Activity activity) {
		this.activity = activity;
		onActivity();
	}

	public void onActivity() {
		cancel();
		inactivityFuture = inactivityTimer.schedule(new FinishListener(activity), INACTIVITY_DELAY_SECONDS, TimeUnit.SECONDS);
	}

	private void cancel() {
		if (inactivityFuture != null) {
			inactivityFuture.cancel(true);
			inactivityFuture = null;
		}
	}

	public void shutdown() {
		cancel();
		inactivityTimer.shutdown();
	}

	private static final class DaemonThreadFactory implements ThreadFactory {
		@Override
		public Thread newThread(Runnable runnable) {
			Thread thread = new Thread(runnable);
			thread.setDaemon(true);
			return thread;
		}
	}
	
	private static void setScanMaxSeconds(int maxSeconds) {
		INACTIVITY_DELAY_SECONDS = maxSeconds;
	}
	
	public static IScanMaxSecondsSetListener getIScanMaxSecondsSetListener() {
		return new IScanMaxSecondsSetListener() {

			@Override
			public void onScanMaxSecondsSet(int max) {
				setScanMaxSeconds(max);
			}
			
		};
	}
	
	public interface IScanMaxSecondsSetListener {
		void onScanMaxSecondsSet(int max);
	}

}
