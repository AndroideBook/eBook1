package com.amaker.ch07.app;

import com.amaker.ch07.app.IPerson;
import com.amaker.ch07.app.R;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
/**
 * 
 * @author 郭宏志
 * RPC 測試
 */
public class MainActivity extends Activity {
	// 宣告IPerson介面
	private IPerson iPerson;
	// 宣告 Button
	private Button btn;
	// 實例化ServiceConnection
	private ServiceConnection conn = new ServiceConnection() {
		//@Override
		synchronized public void onServiceConnected(ComponentName name, IBinder service) {
			// 取得IPerson介面
			iPerson = IPerson.Stub.asInterface(service);
			if (iPerson != null)
				try {
					// RPC 方法呼叫
					iPerson.setName("hz.guo");
					iPerson.setAge(30);
					String msg = iPerson.display();
					// 顯示方法呼叫返回值
					Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
		}

		//@Override
		public void onServiceDisconnected(ComponentName name) {

		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 設定目前視圖佈局
		setContentView(R.layout.main);
		// 實例化Button
		btn = (Button) findViewById(R.id.Button01);
		// 為Button添加單擊事件監聽器
		btn.setOnClickListener(new OnClickListener() {
			//@Override
			public void onClick(View v) {
				// 實例化Intent
				Intent intent = new Intent();
				// 設定Intent Action 屬性
				intent.setAction("com.amaker.ch07.app.action.MY_REMOTE_SERVICE");
				// 繫結服務
				bindService(intent, conn, Service.BIND_AUTO_CREATE);
			}
		});
	}
}