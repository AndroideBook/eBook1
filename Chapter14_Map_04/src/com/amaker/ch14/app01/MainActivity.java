package com.amaker.ch14.app01;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	// Intent Action �`��
	private static String PROXIMITY_ALERT_ACTION_NAME = "com.amaker.ch14.ProximityAlert";
	// �ŧi���sButton���
	private Button btn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // �]�w�ثeActivity�������G��
        setContentView(R.layout.main);
        // �z�LfindViewById��k���oButton���
        btn = (Button)findViewById(R.id.Button01);
        btn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// �]�w
				set();
			}
		});
    }
    
    // �ͪ񴣥ܤ�k
    private void set() {
    	// �w��A�ȱ`��
	    String locService = Context.LOCATION_SERVICE;
	    // �w��A�Ⱥ޲z�����O
	    LocationManager locationManager;
	    // �z�LgetSystemService��k���oLocationManager���
	    locationManager = (LocationManager)getSystemService(locService);
	    // �ŧi�g��
	    double lat = 37.4;
	    // �ŧi�n��
	    double lng = 55.0;
	    // �ŧi�b�|(���G��)
	    float radius = 200f;
	    // ���L��
	    long expiration = -1; 
	    // �ŧiIntent
	    Intent intent = new Intent(PROXIMITY_ALERT_ACTION_NAME);
	    // �ŧiPendingIntent
	    PendingIntent pi = PendingIntent.getBroadcast(this, -1, intent, 0);
	    // �K�[�ͪ�ĵ�i
	    locationManager.addProximityAlert(lat, lng, radius, expiration, pi);
    }
}