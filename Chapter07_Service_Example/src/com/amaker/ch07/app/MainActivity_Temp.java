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

public class MainActivity_Temp extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // 建立Intent
        Intent intent = new Intent();
        // 設定Action屬性
        intent.setAction("com.amaker.ch08.app.action.MY_SERVICE");
        // 啟動該Service
        startService(intent);
        // 停止一個Service
        stopService(intent);
        
        
        // 連接物件
        ServiceConnection conn = new ServiceConnection() {
        	//@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				Log.i("SERVICE", "連線成功！");
			}
        	
			//@Override
			public void onServiceDisconnected(ComponentName name) {
				Log.i("SERVICE", "離線！");
			}
		};
        // 繫結Service
        bindService(intent, conn, Service.BIND_AUTO_CREATE);
    }
}