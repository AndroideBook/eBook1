package com.amaker.ch17.app;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author 郭宏志
 * 提醒用戶
 */
public class AlarmActivity extends Activity {
	public static final int ID = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alarm);
		// 取得Button﹜TextView實例
		Button btn = (Button) findViewById(R.id.cancelButton01);
		TextView tv = (TextView)findViewById(R.id.msgTextView01);
		
		// 取得NotificationManager實例
		String service = Context.NOTIFICATION_SERVICE;
		final NotificationManager nm = (NotificationManager)getSystemService(service);
		// 實例化Notification
		Notification n = new Notification();
		// 設定顯示提示訊息，同時顯示在狀態列
		String msg = getIntent().getStringExtra("msg");
		// 顯示時間
		n.tickerText = msg;
		tv.setText(msg);
		
		// 設定語音提示
		n.sound = Uri.parse("file:///sdcard/fallbackring.ogg");
		// 發出通知
		nm.notify(ID, n);
		// 取消通知
		btn.setOnClickListener(new OnClickListener() {
			//@Override
			public void onClick(View v) {
				nm.cancel(ID);
				finish();
			}
		});
	}
}
