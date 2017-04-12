package com.amaker.ch08.app;

import com.amaker.ch08.app.R;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 
 * @author ������ 
 * ����AlarmManager
 */
public class MainActivity extends Activity {
	// �ŧiButton
	private Button setBtn, cancelBtn;
	// �w�q�s��Action
	private static final String BC_ACTION = "com.amaker.ch08.app.action.BC_ACTION";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// �]�w�ثe�G������
		setContentView(R.layout.main);
		
		// ��Ҥ�Button
		setBtn = (Button) findViewById(R.id.Button01);
		cancelBtn = (Button) findViewById(R.id.Button02);
		
		// ���oAlarmManager���
		final AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
		
		// ��Ҥ�Intent
		Intent intent = new Intent();
		// �]�wIntent action�ݩ�
		intent.setAction(BC_ACTION);
		intent.putExtra("msg", "�A�ӥh�}�|�o�I");
		// ��Ҥ�PendingIntent
		final PendingIntent pi = PendingIntent.getBroadcast(MainActivity.this, 0,
				intent, 0);
		// ���o�t�ήɶ�
		final long time = System.currentTimeMillis();

		// �]�w���s�����ƥ�
		setBtn.setOnClickListener(new OnClickListener() {
			//@Override
			public void onClick(View v) {
				// ������ܡA�q�ثe�ɶ��}�l�A���j5��
				am.setRepeating(AlarmManager.RTC_WAKEUP, time, 8 * 1000, pi);
			}
		});
		
		// �]�w���s�����ƥ�
		cancelBtn.setOnClickListener(new OnClickListener() {
			//@Override
			public void onClick(View v) {
				am.cancel(pi);
			}
		});
	}
}