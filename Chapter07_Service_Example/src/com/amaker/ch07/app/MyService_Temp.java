package com.amaker.ch07.app;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * @author 郭宏志
 * 測試Service
 */
public class MyService_Temp extends Service{
	
	// 可以返回null，通常返回一個有aidl定義的介面
	public IBinder onBind(Intent intent) {
		return null;
	}
	// Service建立時呼叫
	public void onCreate() {
		super.onCreate();
	}
	// 當用戶端呼叫startService()方法啟動Service時，呼叫該方法
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
	}
	// 當Service不再使用時呼叫
	public void onDestroy() {
		super.onDestroy();
	}
}
