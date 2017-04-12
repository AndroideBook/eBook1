package com.amaker.ch08.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
/**
 * @author 郭宏志
 */
public class MyReceiver extends BroadcastReceiver{
	@Override
	public void onReceive(Context context, Intent intent) {
		// 實例化Intent
		Intent i = new Intent();
		// 在新的任務中啟動Activity
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		// 設定Intent啟動的元件名稱
		i.setClass(context, DisplayActivity.class);
		// 啟動Activity顯示通知
		context.startActivity(i);
	}

}
