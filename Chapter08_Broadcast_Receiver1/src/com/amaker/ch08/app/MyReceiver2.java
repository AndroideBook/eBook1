package com.amaker.ch08.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * 
 * @author ������
 * ��ܨt�αҰʧ����s��������
 */
public class MyReceiver2 extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		// ��ܼs���T�� 
		Log.i("my_tag", "BOOT_COMPLETED~~~~~~~~~~~~~~~~");
	}

}
