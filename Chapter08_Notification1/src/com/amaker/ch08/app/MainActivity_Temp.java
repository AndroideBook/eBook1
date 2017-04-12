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
        
        // ��Ҥ�Notification
        Notification n = new Notification();
        // �]�w��ܹϥܡA����ܩ󪬺A�C
        int icon = n.icon = R.drawable.icon; 
        // �]�w��ܴ��ܰT���A�P����ܦb���A�C
        String tickerText = "Test Notification"; 
        // ��ܮɶ�
        long when = System.currentTimeMillis();
        n.icon = icon;
        n.tickerText = tickerText;
        n.when = when;
        
        // �]�i�H�z�L�غc�l�ӳ]�w
        Notification n1 = new Notification(icon, tickerText, when); 
        
        // ��Ҥ�Intent
        Intent intent = new Intent(this, MainActivity_Temp.class); 
        // ���oPendingIntent
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0); 
        // �]�w�ƥ�T��
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
        
        // �Хܳq����ID
        int ID = 1;
        // �o�X�q��
        nm.notify(ID, n);
        
    }
}