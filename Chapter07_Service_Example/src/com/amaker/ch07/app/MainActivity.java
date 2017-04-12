package com.amaker.ch07.app;

import com.amaker.ch07.app.R;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * @author 郭宏志
 * 測試Service
 */

public class MainActivity extends Activity {
	// 宣告Button
	private Button startBtn,stopBtn,bindBtn,unbindBtn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 設定目前佈局視圖
        setContentView(R.layout.main);
        // 實例化Button
        startBtn = (Button)findViewById(R.id.startButton01);
        stopBtn = (Button)findViewById(R.id.stopButton02);
        bindBtn = (Button)findViewById(R.id.bindButton03);
        unbindBtn = (Button)findViewById(R.id.unbindButton04);
        
        // 添加監聽器
        startBtn.setOnClickListener(startListener);
        stopBtn.setOnClickListener(stopListener);
        bindBtn.setOnClickListener(bindListener);
        unbindBtn.setOnClickListener(unBindListener);
        
    }
    // 啟動Service監聽器
    private OnClickListener startListener = new OnClickListener() {
		//@Override
		public void onClick(View v) {
	        // 建立Intent
	        Intent intent = new Intent();
	        // 設定Action屬性
	        intent.setAction("com.amaker.ch07.app.action.MY_SERVICE");
	        // 啟動該Service
	        startService(intent);
		}
	};
	
    // 停止Service監聽器
    private OnClickListener stopListener = new OnClickListener() {
		//@Override
		public void onClick(View v) {
	        // 建立Intent
	        Intent intent = new Intent();
	        // 設定Action屬性
	        intent.setAction("com.amaker.ch07.app.action.MY_SERVICE");
	        // 啟動該Service
	        stopService(intent);
		}
	};
	
   // 連接物件
   private ServiceConnection conn = new ServiceConnection() {
    	//@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.i("SERVICE", "連接成功！");
			Toast.makeText(MainActivity.this, "連接成功！", Toast.LENGTH_LONG).show();
		}
		//@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.i("SERVICE", "離線！");
			Toast.makeText(MainActivity.this, "離線！", Toast.LENGTH_LONG).show();
		}
	};
	
    // 擊結Service監聽器
    private OnClickListener bindListener = new OnClickListener() {
		//@Override
		public void onClick(View v) {
	        // 建立Intent
	        Intent intent = new Intent();
	        // 設定Action監聽器
	        intent.setAction("com.amaker.ch07.app.action.MY_SERVICE");
	     
	        // 繫結Service
	        bindService(intent, conn, Service.BIND_AUTO_CREATE);
		}
	};
	
    // 解除繫結Service監聽器
    private OnClickListener unBindListener = new OnClickListener() {
		//@Override
		public void onClick(View v) {
	        // 建立Intent
	        Intent intent = new Intent();
	        // 設定Action屬性
	        intent.setAction("com.amaker.ch07.app.action.MY_SERVICE");
	        // 解除繫結Service
	        unbindService(conn);
		}
	};
	
}