package com.amaker.ch08.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
/**
 * 
 * @author ������
 *
 */
public class MyReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		// ���o���ܰT��
		String msg = intent.getStringExtra("msg");
		// ��ܴ��ܰT��
		Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
	}
}