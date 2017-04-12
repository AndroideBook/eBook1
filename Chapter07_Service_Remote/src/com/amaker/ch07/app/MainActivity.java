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
 * @author ������
 * RPC ����
 */
public class MainActivity extends Activity {
	// �ŧiIPerson����
	private IPerson iPerson;
	// �ŧi Button
	private Button btn;
	// ��Ҥ�ServiceConnection
	private ServiceConnection conn = new ServiceConnection() {
		//@Override
		synchronized public void onServiceConnected(ComponentName name, IBinder service) {
			// ���oIPerson����
			iPerson = IPerson.Stub.asInterface(service);
			if (iPerson != null)
				try {
					// RPC ��k�I�s
					iPerson.setName("hz.guo");
					iPerson.setAge(30);
					String msg = iPerson.display();
					// ��ܤ�k�I�s��^��
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
		// �]�w�ثe���ϧG��
		setContentView(R.layout.main);
		// ��Ҥ�Button
		btn = (Button) findViewById(R.id.Button01);
		// ��Button�K�[�����ƥ��ť��
		btn.setOnClickListener(new OnClickListener() {
			//@Override
			public void onClick(View v) {
				// ��Ҥ�Intent
				Intent intent = new Intent();
				// �]�wIntent Action �ݩ�
				intent.setAction("com.amaker.ch07.app.action.MY_REMOTE_SERVICE");
				// ô���A��
				bindService(intent, conn, Service.BIND_AUTO_CREATE);
			}
		});
	}
}