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
 * @author ������
 * ����Service
 */

public class MainActivity extends Activity {
	// �ŧiButton
	private Button startBtn,stopBtn,bindBtn,unbindBtn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // �]�w�ثe�G������
        setContentView(R.layout.main);
        // ��Ҥ�Button
        startBtn = (Button)findViewById(R.id.startButton01);
        stopBtn = (Button)findViewById(R.id.stopButton02);
        bindBtn = (Button)findViewById(R.id.bindButton03);
        unbindBtn = (Button)findViewById(R.id.unbindButton04);
        
        // �K�[��ť��
        startBtn.setOnClickListener(startListener);
        stopBtn.setOnClickListener(stopListener);
        bindBtn.setOnClickListener(bindListener);
        unbindBtn.setOnClickListener(unBindListener);
        
    }
    // �Ұ�Service��ť��
    private OnClickListener startListener = new OnClickListener() {
		//@Override
		public void onClick(View v) {
	        // �إ�Intent
	        Intent intent = new Intent();
	        // �]�wAction�ݩ�
	        intent.setAction("com.amaker.ch07.app.action.MY_SERVICE");
	        // �Ұʸ�Service
	        startService(intent);
		}
	};
	
    // ����Service��ť��
    private OnClickListener stopListener = new OnClickListener() {
		//@Override
		public void onClick(View v) {
	        // �إ�Intent
	        Intent intent = new Intent();
	        // �]�wAction�ݩ�
	        intent.setAction("com.amaker.ch07.app.action.MY_SERVICE");
	        // �Ұʸ�Service
	        stopService(intent);
		}
	};
	
   // �s������
   private ServiceConnection conn = new ServiceConnection() {
    	//@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.i("SERVICE", "�s�����\�I");
			Toast.makeText(MainActivity.this, "�s�����\�I", Toast.LENGTH_LONG).show();
		}
		//@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.i("SERVICE", "���u�I");
			Toast.makeText(MainActivity.this, "���u�I", Toast.LENGTH_LONG).show();
		}
	};
	
    // ����Service��ť��
    private OnClickListener bindListener = new OnClickListener() {
		//@Override
		public void onClick(View v) {
	        // �إ�Intent
	        Intent intent = new Intent();
	        // �]�wAction��ť��
	        intent.setAction("com.amaker.ch07.app.action.MY_SERVICE");
	     
	        // ô��Service
	        bindService(intent, conn, Service.BIND_AUTO_CREATE);
		}
	};
	
    // �Ѱ�ô��Service��ť��
    private OnClickListener unBindListener = new OnClickListener() {
		//@Override
		public void onClick(View v) {
	        // �إ�Intent
	        Intent intent = new Intent();
	        // �]�wAction�ݩ�
	        intent.setAction("com.amaker.ch07.app.action.MY_SERVICE");
	        // �Ѱ�ô��Service
	        unbindService(conn);
		}
	};
	
}