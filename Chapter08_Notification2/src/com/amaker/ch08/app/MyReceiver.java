package com.amaker.ch08.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
/**
 * @author ������
 */
public class MyReceiver extends BroadcastReceiver{
	@Override
	public void onReceive(Context context, Intent intent) {
		// ��Ҥ�Intent
		Intent i = new Intent();
		// �b�s�����Ȥ��Ұ�Activity
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		// �]�wIntent�Ұʪ�����W��
		i.setClass(context, DisplayActivity.class);
		// �Ұ�Activity��ܳq��
		context.startActivity(i);
	}

}
