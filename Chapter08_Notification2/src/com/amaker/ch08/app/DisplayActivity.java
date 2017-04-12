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
 * @author 郭宏志
 */
public class DisplayActivity extends Activity {
	// 宣告按鈕
	private Button cancelBtn;
	// 宣告Notification
	private Notification n ;
	// 宣告NotificationManager
	private NotificationManager nm;
	// Notification梓尨ID
	private static final int ID = 1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);
        // 實例化按鈕
        cancelBtn = (Button)findViewById(R.id.cancelButton02);
        // 取得NotificationManager實例
        String service = NOTIFICATION_SERVICE;
        nm = (NotificationManager)getSystemService(service);
        
        // 實例化Notification
        n = new Notification();
        // 設定顯示圖示，並顯示於狀態列
        int icon = n.icon = R.drawable.happy; 
        // 設定顯示提示訊息，同樣顯示在狀態列
        String tickerText = "Test Notification"; 
        // 顯示時間
        long when = System.currentTimeMillis();
        n.icon = icon;
        n.tickerText = tickerText;
        n.when = when;
        
        // 實例化Intent
        Intent intent = new Intent(this, MainActivity.class); 
        // 取得PendingIntent
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0); 
        // 設定事件訊息
        n.setLatestEventInfo(this, "My Title", "My Content", pi); 
        // 通出通知
        nm.notify(ID, n);
        
        // 為按鈕添加監聽器
        cancelBtn.setOnClickListener(cancelListener);
    }
    
	// 取消通知監聽器
	 private OnClickListener cancelListener = new OnClickListener() {
		//@Override
		public void onClick(View v) {
			// 取消通知
			nm.cancel(ID);
		}
	};
}