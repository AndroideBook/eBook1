package com.amaker.ch08.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * 
 * @author 郭宏志
 * 顯示系統啟動完成廣播接收器
 */
public class MyReceiver2 extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		// 顯示廣播訊息 
		Log.i("my_tag", "BOOT_COMPLETED~~~~~~~~~~~~~~~~");
	}

}
