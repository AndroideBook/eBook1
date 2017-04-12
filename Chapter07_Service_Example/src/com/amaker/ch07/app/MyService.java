package com.amaker.ch07.app;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * @author ������
 * ����Service
 */
public class MyService extends Service{
	
	// �i�H��^null�A�q�`��^�@����aidl�w�q������
	public IBinder onBind(Intent intent) {
		Log.i("SERVICE", "onBind..............");
		Toast.makeText(MyService.this, "onBind..............", Toast.LENGTH_LONG).show();
		return null;
	}
	// �إ�Service�ɩI�s
	public void onCreate() {
		Log.i("SERVICE", "onCreate..............");
		Toast.makeText(MyService.this, "onCreate..............", Toast.LENGTH_LONG).show();
	}
	// ��Τ�ݩI�sstartService()��k�Ұ�Service�ɡA�I�s�Ӥ�k
	public void onStart(Intent intent, int startId) {
		Log.i("SERVICE", "onStart..............");
		Toast.makeText(MyService.this, "onStart..............", Toast.LENGTH_LONG).show();
	}
	// ��Service���A�ϥήɩI�s
	public void onDestroy() {
		Log.i("SERVICE", "onDestroy..............");
		Toast.makeText(MyService.this, "onDestroy..............", Toast.LENGTH_LONG).show();
	}
}
