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
 * @author 郭宏志 
 * 測試AlarmManager
 */
public class MainActivity extends Activity {
	// 宣告Button
	private Button setBtn, cancelBtn;
	// 定義廣播Action
	private static final String BC_ACTION = "com.amaker.ch08.app.action.BC_ACTION";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 設定目前佈局視圖
		setContentView(R.layout.main);
		
		// 實例化Button
		setBtn = (Button) findViewById(R.id.Button01);
		cancelBtn = (Button) findViewById(R.id.Button02);
		
		// 取得AlarmManager實例
		final AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
		
		// 實例化Intent
		Intent intent = new Intent();
		// 設定Intent action屬性
		intent.setAction(BC_ACTION);
		intent.putExtra("msg", "你該去開會囉！");
		// 實例化PendingIntent
		final PendingIntent pi = PendingIntent.getBroadcast(MainActivity.this, 0,
				intent, 0);
		// 取得系統時間
		final long time = System.currentTimeMillis();

		// 設定按鈕單擊事件
		setBtn.setOnClickListener(new OnClickListener() {
			//@Override
			public void onClick(View v) {
				// 重複顯示，從目前時間開始，間隔5秒
				am.setRepeating(AlarmManager.RTC_WAKEUP, time, 8 * 1000, pi);
			}
		});
		
		// 設定按鈕單擊事件
		cancelBtn.setOnClickListener(new OnClickListener() {
			//@Override
			public void onClick(View v) {
				am.cancel(pi);
			}
		});
	}
}