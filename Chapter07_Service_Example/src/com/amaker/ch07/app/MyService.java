package com.amaker.ch07.app;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * @author 郭宏志
 * 測試Service
 */
public class MyService extends Service{
	
	// 可以返回null，通常返回一次有aidl定義的介面
	public IBinder onBind(Intent intent) {
		Log.i("SERVICE", "onBind..............");
		Toast.makeText(MyService.this, "onBind..............", Toast.LENGTH_LONG).show();
		return null;
	}
	// 建立Service時呼叫
	public void onCreate() {
		Log.i("SERVICE", "onCreate..............");
		Toast.makeText(MyService.this, "onCreate..............", Toast.LENGTH_LONG).show();
	}
	// 當用戶端呼叫startService()方法啟動Service時，呼叫該方法
	public void onStart(Intent intent, int startId) {
		Log.i("SERVICE", "onStart..............");
		Toast.makeText(MyService.this, "onStart..............", Toast.LENGTH_LONG).show();
	}
	// 當Service不再使用時呼叫
	public void onDestroy() {
		Log.i("SERVICE", "onDestroy..............");
		Toast.makeText(MyService.this, "onDestroy..............", Toast.LENGTH_LONG).show();
	}
}
