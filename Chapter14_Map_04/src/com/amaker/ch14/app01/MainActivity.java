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
	// Intent Action 常數
	private static String PROXIMITY_ALERT_ACTION_NAME = "com.amaker.ch14.ProximityAlert";
	// 宣告按鈕Button實例
	private Button btn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 設定目前Activity的介面佈局
        setContentView(R.layout.main);
        // 透過findViewById方法取得Button實例
        btn = (Button)findViewById(R.id.Button01);
        btn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// 設定
				set();
			}
		});
    }
    
    // 趨近提示方法
    private void set() {
    	// 定位服務常數
	    String locService = Context.LOCATION_SERVICE;
	    // 定位服務管理器類別
	    LocationManager locationManager;
	    // 透過getSystemService方法取得LocationManager實例
	    locationManager = (LocationManager)getSystemService(locService);
	    // 宣告經度
	    double lat = 37.4;
	    // 宣告緯度
	    double lng = 55.0;
	    // 宣告半徑(單位：米)
	    float radius = 200f;
	    // 不過期
	    long expiration = -1; 
	    // 宣告Intent
	    Intent intent = new Intent(PROXIMITY_ALERT_ACTION_NAME);
	    // 宣告PendingIntent
	    PendingIntent pi = PendingIntent.getBroadcast(this, -1, intent, 0);
	    // 添加趨近警告
	    locationManager.addProximityAlert(lat, lng, radius, expiration, pi);
    }
}