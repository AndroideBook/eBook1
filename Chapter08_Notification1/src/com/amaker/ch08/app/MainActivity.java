package com.amaker.ch08.app;

import com.amaker.ch08.app.R;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author ������
 * ���ճq��
 */

public class MainActivity extends Activity {
	// �ŧi���s
	private Button sendBtn,cancelBtn;
	// �ŧiNotification
	private Notification n ;
	// �ŧiNotificationManager
	private NotificationManager nm;
	// Notification�Х�ID
	private static final int ID = 1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // ��Ҥƫ��s
        sendBtn = (Button)findViewById(R.id.sendButton01);
        cancelBtn = (Button)findViewById(R.id.cancelButton02);
        
        // ���oNotificationManager���
        String service = NOTIFICATION_SERVICE;
        nm = (NotificationManager)getSystemService(service);
        
        // ��Ҥ�Notification
        n = new Notification();
        // �]�w��ܹϥܡA����ܩ󪬺A�C
        int icon = n.icon = R.drawable.happy; 
        // �]�w��ܴ��ܰT���A�P����ܦb���A�C
        String tickerText = "Test Notification"; 
        // ��ܮɶ�
        long when = System.currentTimeMillis();
        n.icon = icon;
        n.tickerText = tickerText;
        n.when = when;
        // �����s�K�[��ť��
        sendBtn.setOnClickListener(sendListener);
        cancelBtn.setOnClickListener(cancelListener);
        
    }
    // �o�e�q����ť��
    private OnClickListener sendListener = new OnClickListener() {
		//@Override
		public void onClick(View v) {
	        // ��Ҥ�Intent
	        Intent intent = new Intent(MainActivity.this, MainActivity.class); 
	        // ���oPendingIntent
	        PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 0, intent, 0); 
	        // �]�w�ƥ�T��
	        n.setLatestEventInfo(MainActivity.this, "My Title", "My Content", pi); 
	        // �o�X�q��
	        nm.notify(ID, n);
		}
	};
	// �����q����ť��
	 private OnClickListener cancelListener = new OnClickListener() {
		//@Override
		public void onClick(View v) {
			// �����q��
			nm.cancel(ID);
		}
	};
}