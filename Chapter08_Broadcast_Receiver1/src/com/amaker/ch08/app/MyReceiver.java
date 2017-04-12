package com.amaker.ch08.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * @author ������
 * �����s��
 */
public class MyReceiver extends BroadcastReceiver{
	
	@Override
	public void onReceive(Context cxt, Intent intent) {
		// �qIntent�����o�T��
		String msg = intent.getStringExtra("msg");
		// �ϥ�Toast���
		Toast.makeText(cxt, msg, Toast.LENGTH_LONG).show();
	}
}
