package com.amaker.ch07.app;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * @author ������
 * ����Service
 */
public class MyService_Temp extends Service{
	
	// �i�H��^null�A�q�`��^�@�Ӧ�aidl�w�q������
	public IBinder onBind(Intent intent) {
		return null;
	}
	// Service�إ߮ɩI�s
	public void onCreate() {
		super.onCreate();
	}
	// ��Τ�ݩI�sstartService()��k�Ұ�Service�ɡA�I�s�Ӥ�k
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
	}
	// ��Service���A�ϥήɩI�s
	public void onDestroy() {
		super.onDestroy();
	}
}
