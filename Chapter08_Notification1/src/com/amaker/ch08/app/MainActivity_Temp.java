package com.amaker.ch08.app;

import com.amaker.ch08.app.R;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Audio;

public class MainActivity_Temp extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        String service = NOTIFICATION_SERVICE;
        NotificationManager nm = (NotificationManager)getSystemService(service);
        
        // 實例化Notification
        Notification n = new Notification();
        // 設定顯示圖示，並顯示於狀態列
        int icon = n.icon = R.drawable.icon; 
        // 設定顯示提示訊息，同樣顯示在狀態列
        String tickerText = "Test Notification"; 
        // 顯示時間
        long when = System.currentTimeMillis();
        n.icon = icon;
        n.tickerText = tickerText;
        n.when = when;
        
        // 也可以透過建構子來設定
        Notification n1 = new Notification(icon, tickerText, when); 
        
        // 實例化Intent
        Intent intent = new Intent(this, MainActivity_Temp.class); 
        // 取得PendingIntent
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0); 
        // 設定事件訊息
        n.setLatestEventInfo(this, "My Title", "My Content", pi); 
        
        n.defaults |= Notification.DEFAULT_SOUND; 
        n.sound = Uri.parse("file:///sdcard/sound.mp3"); 
        n.sound = Uri.withAppendedPath(Audio.Media.INTERNAL_CONTENT_URI, "6"); 
        
        n.defaults |= Notification.DEFAULT_VIBRATE; 
        long[] vibrate = {0,50,100,150}; 
        n.vibrate = vibrate; 
        
        n.defaults |= Notification.DEFAULT_LIGHTS; 
        n.ledARGB = 0xff00ff00; 
        n.ledOnMS = 300; 
        n.ledOffMS = 1000; 
        n.flags |= Notification.FLAG_SHOW_LIGHTS; 
        
        // 標示通知的ID
        int ID = 1;
        // 發出通知
        nm.notify(ID, n);
        
    }
}